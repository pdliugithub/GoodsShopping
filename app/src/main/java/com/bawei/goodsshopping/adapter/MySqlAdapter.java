package com.bawei.goodsshopping.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.goodsshopping.db.MyOpenHelper;
import com.bawei.goodsshopping.fragment.KnowThingFragment;
import com.bawei.goodsshopping.main.R;
import com.bawei.goodsshopping.vo.CartInfo;
import com.bawei.goodsshopping.vo.SqlInfo;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2016/3/25.
 */
public class MySqlAdapter extends BaseAdapter {

    ImageLoader imageLoader =ImageLoader.getInstance();
    private List<CartInfo> mSqlList;
    private Context mContext;
    MySqlAdapter adapter;
    private TextView mTextViewTotal;
    private static int mTotalPrice = 0;


    public MySqlAdapter(List<CartInfo> mSqlList, Context mContext ,TextView tv) {
        this.mSqlList = mSqlList;
        this.mContext = mContext;
        adapter = this;
        this.mTextViewTotal = tv;
    }

    @Override
    public int getCount() {
        return mSqlList.size();
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

        if(convertView == null){

            vh = new ViewHolder();

            convertView = View.inflate(mContext, R.layout.know_item ,null);

            vh.cb = (CheckBox) convertView.findViewById(R.id.know_item_cb);
            vh.img = (ImageView) convertView.findViewById(R.id.know_item_img);
            vh.delete = (ImageView) convertView.findViewById(R.id.know_item_delete);
            vh.name = (TextView) convertView.findViewById(R.id.know_item_name);
            vh.salePrice = (TextView) convertView.findViewById(R.id.know_item_salePrice);

            vh.add = (TextView) convertView.findViewById(R.id.know_item_add);
            vh.count = (TextView) convertView.findViewById(R.id.know_item_count);
            vh.cut = (TextView) convertView.findViewById(R.id.know_item_cut);

            convertView.setTag(vh);
        }

        vh = (ViewHolder) convertView.getTag();

        final CartInfo info = mSqlList.get(position);

        imageLoader.displayImage(info.getList_image(), vh.img);
        vh.name.setText(info.getUname());
        vh.salePrice.setText("¥:"+info.getSale_price());
        vh.cb.setChecked(info.isChecked());
//        vh.count.setText(""+info.getCart_count());

        final View finalConvertView = convertView;

//        //复选框
        vh.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    finalConvertView.setBackgroundResource(R.drawable.category_selection_gridview_bg);

                    mTotalPrice += info.getSale_price();
                    mTextViewTotal.setText("¥:"+mTotalPrice);

                } else {
                    finalConvertView.setBackgroundColor(Color.WHITE);
                    mTotalPrice -= info.getSale_price();
                    mTextViewTotal.setText("¥:"+mTotalPrice);

                }

            }
        });

        //删除
        vh.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final View view = v;
                AlertDialog dialog = new AlertDialog.Builder(mContext)
                        .setTitle("主人 help me....")
                        .setIcon(R.mipmap.shake_fail_icon)
                        .setMessage("你确定要删除此商品")
                        .setNegativeButton("手下留情", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })

                        .setPositiveButton("视而不管", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                //集合删除、更新适配器
                                mSqlList.remove(info);
                                adapter.notifyDataSetChanged();


                                MyOpenHelper helper = new MyOpenHelper(mContext, "test.db", null, 1);
                                SQLiteDatabase db = helper.getWritableDatabase();

                                db.execSQL("delete from goods where product_id = ?", new Object[]{info.getProduct_id()});
                                db.close();//关闭数据库
                                Snackbar.make(view, "删除成功", Snackbar.LENGTH_SHORT).show();
                            }
                        })
                        .show();


                Window window = dialog.getWindow();
                WindowManager.LayoutParams params = window.getAttributes();
                params.alpha=0.8f;
                window.setAttributes(params);


            }
        });


        //添加count
        int count = 1;

//        MyOnClickListener click = new MyOnClickListener(count,vh,info,mTextViewTotal);

//        vh.add.setOnClickListener(click);
//        vh.cut.setOnClickListener(click);


        return convertView;
    }

    static class ViewHolder{

        private CheckBox cb;
        private ImageView img ,delete;
        private TextView name,salePrice,cut,count,add;

    }

//     static class MyOnClickListener implements View.OnClickListener {
//
//        private static  int count = 1;
//        private ViewHolder vh;
//         private CartInfo info;
//         private TextView textTotal;
//
//
//        public MyOnClickListener(int count,ViewHolder vh,CartInfo info , TextView textTotal) {
//            this.count = count;
//            this.vh = vh;
//            this.info = info;
//            this.textTotal = textTotal;
//        }


//        @Override
//        public void onClick(View v) {
//
//            int id =v.getId();
//
//            switch (id){
//
//                case R.id.know_item_add:
//
//                    count = count+1;
//                    vh.count.setText(""+count);
//
//                    mTotalPrice -= info.getSale_price() * MyOnClickListener.count;
//                    textTotal.setText("¥:"+mTotalPrice);
//
//                    break;
//
//                case R.id.know_item_cut:
//
//                    if(count==0){
//                        return ;
//                    }
//                    count = count-1;
//                    vh.count.setText(""+count);
//
//                    mTotalPrice -= info.getSale_price() * MyOnClickListener.count;
//                    textTotal.setText("¥:"+mTotalPrice);
//
//                    break;
//
//
//            }

//        }

//         public int getCount() {
//             return count;
//         }
//
//         public void setCount(int count) {
//             this.count = count;
//         }
//     }


}
