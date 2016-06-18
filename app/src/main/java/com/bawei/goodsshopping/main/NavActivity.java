package com.bawei.goodsshopping.main;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class NavActivity extends AppCompatActivity {

    private Handler handler = null;
    private int delayTime = 3000;

    private MyApplication myApplication = MyApplication.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

        myApplication.addActivity(this);

        ImageView imageView = (ImageView) findViewById(R.id.nav_img);

        imageView.setBackgroundResource(R.mipmap.firstpic);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(NavActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },delayTime);



    }
}
