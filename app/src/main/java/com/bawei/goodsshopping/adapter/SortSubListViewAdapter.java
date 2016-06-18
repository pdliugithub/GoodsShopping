package com.bawei.goodsshopping.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bawei.goodsshopping.main.R;

import java.util.List;

/**
 * Created by Administrator on 2016/3/24.
 */
public class SortSubListViewAdapter extends BaseAdapter {

    private int seletItem = -1;  //item选中状态

    private Context mContext;
    private List<String> strTitle;

    public SortSubListViewAdapter(Context mContext, List<String> strTitle) {
        this.mContext = mContext;
        this.strTitle = strTitle;
    }

    public void setSeletItem(int seletItem) {
        this.seletItem = seletItem;
    }

    @Override
    public int getCount() {
        return strTitle.size();
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

        ViewHolder vh = null;

        if(convertView == null){

            vh = new ViewHolder();

            convertView = View.inflate(mContext, R.layout.text1,null);

            vh.textView = (TextView) convertView.findViewById(R.id.text1);


            convertView.setTag(vh);
        }

        vh = (ViewHolder) convertView.getTag();

        if(position == seletItem){
            vh.textView.setBackgroundResource(R.mipmap.abs__cab_background_top_holo_light);
        }else{
            vh.textView.setBackgroundColor(Color.WHITE);
        }

        String name = strTitle.get(position);

        vh.textView.setText(name);

        return convertView;
    }

    class ViewHolder{

        private TextView textView;
    }
}
