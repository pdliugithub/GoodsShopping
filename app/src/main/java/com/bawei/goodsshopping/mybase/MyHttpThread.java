package com.bawei.goodsshopping.mybase;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.widget.Toast;


/**
 * Created by Administrator on 2016/3/30.
 */
public class MyHttpThread extends Thread {

    private Context mContext;
    private static Handler handler = new Handler();

    public MyHttpThread(String threadName, Context mContext) {
        super(threadName);
        this.mContext = mContext;
    }

    @Override
    public void run() {

        boolean state = true;
        if(state){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(mContext, "网络状态良好", Toast.LENGTH_SHORT).show();
                }
            });
        }

        if(!state){

            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(mContext, "网络堵塞。。。", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }


}
