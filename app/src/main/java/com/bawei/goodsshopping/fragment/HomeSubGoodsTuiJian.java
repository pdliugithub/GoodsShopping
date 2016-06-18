package com.bawei.goodsshopping.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.goodsshopping.adapter.SortGridViewAdapter;
import com.bawei.goodsshopping.adapter.SortSubListViewAdapter;
import com.bawei.goodsshopping.main.R;
import com.bawei.goodsshopping.vo.SortBrandCategory;
import com.bawei.goodsshopping.vo.SortDataParse;
import com.bawei.goodsshopping.vo.SortResult;
import com.bawei.goodsshopping.vo.SortSecond;
import com.bawei.goodsshopping.vo.SortThird;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 *
 * 商品推荐
 */
public class HomeSubGoodsTuiJian extends Fragment implements AdapterView.OnItemClickListener,SwipeRefreshLayout.OnRefreshListener,AdapterView.OnItemSelectedListener{

    private static Handler handler = new Handler();

    private List<String> mStrList = new ArrayList<>();  //ListView数据集合
    private List<SortBrandCategory> mBrandCategory = new ArrayList<>();  //Horizontal数据集合
    private List<String> mGridSort = new ArrayList<>();  //GridView Sort数据集合
    private List<SortThird> mSortThirdList = new ArrayList<>();  //GridView 适配数据集合

    private List<SortResult>  sortResultList = new ArrayList<>();

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView mListView;
    private GridView mGridView;

    private SortSubListViewAdapter listViewAdapter;

    private View view;

    private static final String DefaultHttp = "http://platform.okbuy.com/app/v12/focus/virtual_category";

    public HomeSubGoodsTuiJian() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home_sub_goods_tui_jian, container, false);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.fragment_home_sub_goods_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorGreen,R.color.colorGreenDark,R.color.colorYellow,R.color.colorDark);
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.GRAY);
        mSwipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);//One of DEFAULT, or LARGE.
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mListView = (ListView) view.findViewById(R.id.fragment_home_sub_goods_listView);
        mListView.setOnItemClickListener(this);
        mGridView = (GridView) view.findViewById(R.id.fragment_home_sub_goods_gridView);



        onMyHttpRequest(DefaultHttp);



        return view;
    }

    private void onMyHttpRequest(String defaultHttp) {

        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url(defaultHttp)
                .build();

        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(final Request request, IOException e) {

                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        /*
                        Error提示
                         */
                        Toast.makeText(getActivity(), "Error" + request.body().toString(), Toast.LENGTH_SHORT).show();

                    }
                });

            }

            @Override
            public void onResponse(Response response) throws IOException {

                ResponseBody body = response.body();

                String responseData = body.string();

                onParseData(responseData);

                /*
                更新UI线程
                 */
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        listViewAdapter = new SortSubListViewAdapter(getActivity(), mStrList);
                        listViewAdapter.setSeletItem(0);
                        mListView.setAdapter(listViewAdapter);

                        //GridView默认显示
                        onGridViewAdapter(0);


                    }
                });


            }
        });


    }


    /*

     */
    private void onParseData(String jsonData){

        Gson gson = new Gson();

        SortDataParse sortDataParse= gson.fromJson(jsonData, SortDataParse.class);

        sortResultList = sortDataParse.getResults();

        if(sortResultList == null){
            return ;
        }

        if(mStrList!=null){
            mStrList.clear();
        }
        for(int i=0;i<sortResultList.size();i++){

            SortResult sortResult = sortResultList.get(i);

            String virtual_name = sortResult.getVirtual_name();

            mStrList.add(virtual_name); //添加到ListView集合中

        }


    }

    /**
     * Item点击监听
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        /*
        更新适配器
         */
        listViewAdapter.setSeletItem(position);
        listViewAdapter.notifyDataSetInvalidated();
        onGridViewAdapter(position);

    }

    private void onGridViewAdapter(int position) {

        SortResult sortResult =  sortResultList.get(position);
        List<SortThird> sortThirdList = sortResult.getSecond().get(0).getThird();

        List<SortBrandCategory> sortBrandCategoryList = sortResult.getBrand_category();

        if(sortThirdList == null){
            return;
        }

        //更新适配器
        SortGridViewAdapter adapter = new SortGridViewAdapter(getActivity(),sortThirdList);
        mGridView.setAdapter(adapter);
    }

    /**
    刷新数据
     */
    @Override
    public void onRefresh() {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                mSwipeRefreshLayout.setRefreshing(false);
            }
        } , 3000);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

//        view.setBackgroundColor(Color.BLUE);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

//        parent.setBackgroundColor(Color.RED);
    }
}
