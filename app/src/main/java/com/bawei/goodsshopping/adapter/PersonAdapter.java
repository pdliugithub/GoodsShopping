package com.bawei.goodsshopping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.goodsshopping.main.R;

import java.util.List;

/**
 * Created by Administrator on 2016/3/29.
 */
public class PersonAdapter extends BaseAdapter {

    private String[] str;
    private Context mContext;

    public PersonAdapter(String[] str, Context mContext) {
        this.str = str;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return str.length;
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

            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_person,null);

            vh.name = (TextView) convertView.findViewById(R.id.id_fragment_person_item_name);
            vh.img = (ImageView) convertView.findViewById(R.id.id_fragment_person_item_img);

            convertView.setTag(vh);

        }else{
            vh = (ViewHolder) convertView.getTag();
        }

        String string = str[position];

        vh.name.setText(string);



        return convertView;
    }

    static class ViewHolder{

        private TextView name;
        private ImageView img;

    }
}
