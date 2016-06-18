package com.bawei.goodsshopping.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.goodsshopping.main.R;
import com.bawei.goodsshopping.vo.SortSubResultData;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.List;

/**
 * Created by Administrator on 2016/3/22.
 */
public class BlankFragmentAdapter extends BaseAdapter {

    ImageLoader imageLoader = ImageLoader.getInstance();
    private Context mContext;
    private List<SortSubResultData> mList;

    public BlankFragmentAdapter(Context mContext, List<SortSubResultData> mList) {
        this.mContext = mContext;
        this.mList = mList;
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

            convertView = View.inflate(mContext, R.layout.blank_item,null);

            vh.imageView = (ImageView) convertView.findViewById(R.id.blank_item_img);
            vh.name = (TextView) convertView.findViewById(R.id.blank_item_name);
            vh.salePrice = (TextView) convertView.findViewById(R.id.blank_item_salePrice);
            vh.smarkPrice = (TextView) convertView.findViewById(R.id.blank_item_smarkePrice);
            vh.discount = (TextView) convertView.findViewById(R.id.blank_item_discount);


            convertView.setTag(vh);
        }

        vh = (ViewHolder) convertView.getTag();

        SortSubResultData data = mList.get(position);

        vh.name.setText(data.getName());
        vh.salePrice.setText("¥：" + data.getSale_price());
        vh.smarkPrice.setText(data.getMarket_price());
        vh.smarkPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //添加删除线
        vh.discount.setText(data.getDiscount());

        imageLoader.displayImage(data.getList_image(), vh.imageView, new ImageLoadingListener() {
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

    static class  ViewHolder{

        private TextView name,salePrice,smarkPrice,discount;
        private ImageView imageView;

    }

}
