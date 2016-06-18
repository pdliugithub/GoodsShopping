package com.bawei.goodsshopping.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.goodsshopping.main.R;
import com.bawei.goodsshopping.vo.InfoList;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.List;

/**
 * Created by Administrator on 2016/3/17.
 */
public class HomeListAdapter extends BaseAdapter {

    private List<InfoList> mList;
    private Context mContext;
    private ImageLoader imageLoader = ImageLoader.getInstance();

    public HomeListAdapter(List<InfoList> mList, Context mContext) {
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

        ViewHolder vh = null;

        if(convertView == null){
            vh = new ViewHolder();

            convertView = View.inflate(mContext,R.layout.home_list_item ,null);
            vh.textView = (TextView) convertView.findViewById(R.id.home_list_item_tv);
            vh.imageView = (ImageView) convertView.findViewById(R.id.home_list_item_img);

            convertView.setTag(vh);

        }

        vh = (ViewHolder) convertView.getTag();

        InfoList infoList= mList.get(position);

        vh.textView.setText(infoList.getTitle());
        imageLoader.displayImage(infoList.getImage(), vh.imageView, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {

            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {

            }

            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        });


        return convertView;
    }

    static class ViewHolder{

        private ImageView imageView;
        private TextView textView;

    }
}
