package com.bawei.goodsshopping.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/3/24.
 *
 * 数据库操作
 */
public class MyOpenHelper extends SQLiteOpenHelper {

    public MyOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //数据库第一次创建时候调用，
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table goods(uid integer primary key autoincrement, uname varchar(20), product_id integer ,list_image varchar(20)," +
                "sale_price integer , market_price integer , category_name varchar(20))");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
