package com.bawei.goodsshopping.fragment;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.bawei.goodsshopping.adapter.MySqlAdapter;
import com.bawei.goodsshopping.db.MyOpenHelper;
import com.bawei.goodsshopping.main.R;
import com.bawei.goodsshopping.vo.CartInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 *
 * 知物
 */
public class KnowThingFragment extends android.support.v4.app.Fragment implements SwipeRefreshLayout.OnRefreshListener,CompoundButton.OnCheckedChangeListener{


    private MyOpenHelper mMyOpenHelper; //数据库操作
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView mListView;
    private Handler handler = new Handler();
    private List<CartInfo> mSqlList;

    private MySqlAdapter adapter;

    private TextView mTextViewTotalPrice;   //
    private CheckBox mCbAll;


    public KnowThingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_know_thing, container, false);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.id_know_swipeRefresh);
        mSwipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorGreen);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mListView = (ListView) view.findViewById(R.id.id_know_listView);

        mTextViewTotalPrice = (TextView) view.findViewById(R.id.id_know_totalPrice);

        mCbAll = (CheckBox)view.findViewById(R.id.id_know_cb_all);
        mCbAll.setOnCheckedChangeListener(this);
//        onQueryDB();

        return view;
    }

    /**
     * 查询数据库
     */
    private void onQueryDB() {

        /*
        查询数据库
         */
        mMyOpenHelper = new MyOpenHelper(getActivity(),"test.db",null,1);
        SQLiteDatabase db = mMyOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from goods", null);

        mSqlList = new ArrayList<>();   //实例化
        CartInfo info;
        //循环遍历
        while(cursor.moveToNext()){

            info = new CartInfo();
            info.setUid(cursor.getInt(cursor.getColumnIndex("uid")));    //自增ID
            info.setUname(cursor.getString(cursor.getColumnIndex("uname")));    //
            info.setProduct_id(cursor.getInt(cursor.getColumnIndex("product_id"))); //商品ID
            info.setList_image(cursor.getString(cursor.getColumnIndex("list_image")));  //商品图片URL
            info.setSale_price(cursor.getInt(cursor.getColumnIndex("sale_price"))); //商品的售价
            info.setMarket_price(cursor.getInt(cursor.getColumnIndex("market_price")));   //商品的标价
            info.setCategory_name(cursor.getString(cursor.getColumnIndex("category_name")));    //商品品牌名称
            info.setIsChecked(false);

            mSqlList.add(info);

        }

        /*
        适配数据
         */

        if(mSqlList!=null){
            adapter= new MySqlAdapter(mSqlList , getActivity() ,mTextViewTotalPrice);
            mListView.setAdapter(adapter);
        }

    }


    @Override
    public void onRefresh() {

    //根据实际情况，是否---onQueryDB();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if(mSwipeRefreshLayout.isRefreshing()){

                    mSwipeRefreshLayout.setRefreshing(false);
                }

            }
        } , 2000);




    }

    /**
     * 重写
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {

        if(!hidden){
            //查询 数据库
            onQueryDB();
        }

    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


        if(isChecked){  //全选

            for(int i=0;i<mSqlList.size();i++){
                mSqlList.get(i).setIsChecked(true);
                adapter.notifyDataSetChanged();
            }

        }

        if(!isChecked){ //反选
            for(int i=0;i<mSqlList.size();i++){
                mSqlList.get(i).setIsChecked(false);
                adapter.notifyDataSetChanged();
            }

        }

    }
}
