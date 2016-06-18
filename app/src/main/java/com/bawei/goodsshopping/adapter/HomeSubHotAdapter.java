package com.bawei.goodsshopping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.goodsshopping.main.R;
import com.bawei.goodsshopping.vo.HotRootData;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;

import java.util.List;

/**
 * Created by Administrator on 2016/3/15.
 */
public class HomeSubHotAdapter extends BaseAdapter{

    private List<HotRootData> mlist;
    private Context mContext;
    ImageLoader imageLoader =ImageLoader.getInstance();

    public HomeSubHotAdapter(List<HotRootData> mlist, Context mContext) {
        this.mlist = mlist;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mlist.size();
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
        ViewHolder vh =null;
        if(convertView == null){

            vh = new ViewHolder();

            convertView = LayoutInflater.from(mContext).inflate(R.layout.fragment_home_sub_hot_item,null);
            vh.name = (TextView) convertView.findViewById(R.id.fragment_home_sub_hot_item_name);
            vh.imageView = (ImageView) convertView.findViewById(R.id.fragment_home_sub_hot_item_img);
            vh.short_description= (TextView) convertView.findViewById(R.id.fragment_home_sub_hot_item_short_description);
            vh.description = (TextView) convertView.findViewById(R.id.fragment_home_sub_hot_item_description);

            convertView.setTag(vh);

        }

        vh = (ViewHolder) convertView.getTag();

        HotRootData data = mlist.get(position);

        vh.name.setText(data.getName());
        vh.short_description.setText(data.getShort_description());
        vh.description.setText(data.getDescription());

        imageLoader.displayImage(data.getImg(),vh.imageView);

        return convertView;
    }

    static class ViewHolder{

        private TextView name,short_description,description;
        private ImageView imageView;

    }
}
