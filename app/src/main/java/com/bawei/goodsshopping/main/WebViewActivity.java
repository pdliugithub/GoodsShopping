package com.bawei.goodsshopping.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {

    private WebView mWebView;


    private MyApplication myApplication = MyApplication.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        myApplication.addActivity(this);

        Intent intent = getIntent();
        String link = intent.getStringExtra("link");

        mWebView = (WebView)findViewById(R.id.webView);
        mWebView.loadUrl(link);

        /*
        重写
         */
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);

                return true;
            }
        });

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        finish();
        overridePendingTransition(R.anim.activity_zoom_in, R.anim.activity_zoom_out);
        return true;
    }
}
