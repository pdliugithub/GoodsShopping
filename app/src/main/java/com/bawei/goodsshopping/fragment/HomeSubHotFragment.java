package com.bawei.goodsshopping.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.bawei.goodsshopping.adapter.HomeSubHotAdapter;
import com.bawei.goodsshopping.main.R;
import com.bawei.goodsshopping.main.WebViewActivity;
import com.bawei.goodsshopping.vo.Channel;
import com.bawei.goodsshopping.vo.HomeSubHotParseVo;
import com.bawei.goodsshopping.vo.HotRootData;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 *
 * 热卖商品
 */
public class HomeSubHotFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener
        ,AbsListView.OnScrollListener
        ,AdapterView.OnItemClickListener{


    private static final String  DEFAULT_HTTP_URL = "http://apk.gou.gionee.com//api/apk_v015_index/channel_list?uid=fcdf90bad9454c94a400f3ba2411e935&version=1";

    private static Handler handle = new Handler();

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private HomeSubHotAdapter mHomeSubHotAdapter;
    private ListView mListView;
    private  List<HotRootData> dataList;

    private View mSnackBarView;

    public HomeSubHotFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_sub_hot, container, false);

        mListView = (ListView)view.findViewById(R.id.id_fragment_home_sub_listView);
        mListView.setOnItemClickListener(this);
        mListView.setOnScrollListener(this);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.id_fragment_home_sub_swipeRefresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorYellow, R.color.colorAccent, R.color.colorPrimary);
        mSwipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);//One of DEFAULT, or LARGE.
        mSwipeRefreshLayout.setHorizontalScrollBarEnabled(true);
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.GRAY);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        /*
        首次进入，刷新数据
         */
        onRefresh();
        mSwipeRefreshLayout.setRefreshing(true);


        // Inflate the layout for this fragment
        return view;

    }


    @Override
    public void onRefresh() {

        /*
        Http Request Data   请求数据
         */
        String str =null;
        onRequestData(DEFAULT_HTTP_URL,str);

    }

    private void onRequestData(String defaultHttpUrl,final String back) {

        //创建OKHttp对象
        OkHttpClient okHttpClient = new OkHttpClient();

        //Request
        Request request = new Request.Builder()
                .url(defaultHttpUrl)
                .build();

        //new Call
        Call call = okHttpClient.newCall(request);

        //请求调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {


                String data = response.body().string();

                /*
                Gson解析json字符串
                 */


                dataList = new ArrayList<>();
                List<HotRootData> list =  onParseDataByGson(data);
                dataList.addAll(list);

                handle.postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        mHomeSubHotAdapter = new HomeSubHotAdapter(dataList, getActivity());
                        mListView.setAdapter(mHomeSubHotAdapter);
//                        Toast.makeText(getActivity(), "客官，小二为你加载完成", Toast.LENGTH_SHORT).show();

                        mSwipeRefreshLayout.setRefreshing(false);

                    }
                } ,2000);


            }
        });

    }


    /**
     *
     */

    private List<HotRootData> onParseDataByGson(String json){

        List<HotRootData> hotRootData  = new ArrayList<>();
        Gson gson=new Gson();

        HomeSubHotParseVo parseVo =  gson.fromJson(json, HomeSubHotParseVo.class);
        List<Channel> channel = parseVo.getData().getChannel();

        for (int i=0;i<channel.size();i++){

            List<HotRootData> data = channel.get(i).getChannel();

            for(int j=0;j<data.size();j++){

                HotRootData hotRootData1 = data.get(j);
                hotRootData.add(hotRootData1);
            }

        }


        return hotRootData;

    }

    /**
    ListView Item Click
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String link = dataList.get(position).getLink();

        Intent intent = new Intent(getActivity(),WebViewActivity.class);
        intent.putExtra("link",link);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.activity_zoom_in,R.anim.activity_zoom_out);

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

        if(scrollState == SCROLL_STATE_IDLE){
            if(total == totalCount){

                Toast.makeText(getActivity(), "哦，已经滑到底部啦。。", Toast.LENGTH_SHORT).show();


            }

        }

    }

    int total;
    int totalCount;
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        totalCount = totalItemCount;

        total = firstVisibleItem + visibleItemCount;

    }
}
