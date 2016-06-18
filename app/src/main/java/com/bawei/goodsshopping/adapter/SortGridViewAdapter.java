package com.bawei.goodsshopping.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.goodsshopping.main.R;
import com.bawei.goodsshopping.main.SubSortActivity;
import com.bawei.goodsshopping.vo.SortThird;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;

import java.util.List;

/**
 * Created by Administrator on 2016/3/21.
 */
public class SortGridViewAdapter extends BaseAdapter {

    private ImageLoader imageLoader = ImageLoader.getInstance();

    private Context mContext;
    private List<SortThird> sortThirdList;

    public SortGridViewAdapter(Context mContext, List<SortThird> sortThirdList) {
        this.mContext = mContext;
        this.sortThirdList = sortThirdList;
    }

    @Override
    public int getCount() {

        return sortThirdList.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder vh = null;

        if(convertView ==null){

            vh = new ViewHolder();

            convertView = View.inflate(mContext, R.layout.linear,null);

            vh.img = (ImageView) convertView.findViewById(R.id.img);
            vh.info = (TextView) convertView.findViewById(R.id.tv_info);

            convertView.setTag(vh);
        }
        vh = (ViewHolder) convertView.getTag();

        final SortThird sortThird = sortThirdList.get(position);
        vh.info.setText(sortThird.getVirtual_name());
        ImageSize imageSize = new ImageSize(200,200);
        imageLoader.displayImage(sortThird.getVirtual_icon(), vh.img,imageSize);

        vh.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, SubSortActivity.class);

                intent.putExtra("sortThird", sortThird);
                mContext.startActivity(intent);
                Activity activity = (Activity) mContext;
                activity.overridePendingTransition(R.anim.activity_zoom_in,R.anim.activity_zoom_out);


            }
        });


        return convertView;
    }

    static class ViewHolder{

        private ImageView img;
        private TextView info;


    }
}
