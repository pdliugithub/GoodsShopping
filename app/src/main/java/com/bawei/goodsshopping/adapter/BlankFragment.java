package com.bawei.goodsshopping.adapter;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;

import com.bawei.goodsshopping.main.GoodsDetailsActivity;
import com.bawei.goodsshopping.main.R;
import com.bawei.goodsshopping.vo.SortSubParse;
import com.bawei.goodsshopping.vo.SortSubResultData;
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
 * A simple {@link } subclass.
 *
 * 商品根据 ：普通，价格，最新 进行分类排序
 *
 */
public class BlankFragment extends Fragment implements AdapterView.OnItemClickListener{

    private Handler handler = new Handler();
    private String httpUrl;

    private GridView mGridView;
    List<SortSubResultData> dataList;

    public BlankFragment(String defaultStr) {

        this.httpUrl = defaultStr;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        mGridView = (GridView) view.findViewById(R.id.blank_fragment_gridView);
        mGridView.setOnItemClickListener(this);

        final FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mGridView.smoothScrollToPosition(0);
            }
        });

        mGridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                if(firstVisibleItem > 0){
                   if(!fab.isShown()){
                       fab.show();
                   }
                }else{

                    if(fab.isShown()){
                        fab.hide();
                    }

                }
            }
        });

        //请求数据
        onHttpData();

        return view;
    }


    private void onHttpData(){
        OkHttpClient okHttpClient = new OkHttpClient();

        Request request =  new Request.Builder()
                .url(httpUrl)
                .build();
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {

                ResponseBody body = response.body();

                String data =body.string();

                Gson gson = new Gson();
                SortSubParse sortSubParse = gson.fromJson(data, SortSubParse.class);

                dataList = sortSubParse.getResults().getResults();

                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        BlankFragmentAdapter adapter = new BlankFragmentAdapter(getActivity(),dataList);
                        mGridView.setAdapter(adapter);
                    }
                });

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        String product_id =dataList.get(position).getProduct_id();

        Intent intent = new Intent(getActivity(), GoodsDetailsActivity.class);
        intent.putExtra("product_id", product_id);
        getActivity().startActivity(intent);
        getActivity().overridePendingTransition(R.anim.activity_zoom_in,R.anim.activity_zoom_out);



    }
}
