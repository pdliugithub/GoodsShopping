package com.bawei.goodsshopping.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/3/15.
 */
public abstract class MyBaseListViewAdapter<T> extends BaseAdapter {

    private List<T> mList;
    private Context mContext;

    public MyBaseListViewAdapter(List<T> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        /*
        getView
         */

        View view = onGetView(position,convertView,parent);

        return view;
    }

    public abstract View onGetView(int position,View convertView,ViewGroup parent);
}
