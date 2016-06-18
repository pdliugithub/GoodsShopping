package com.bawei.goodsshopping.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.goodsshopping.adapter.BlankFragment;
import com.bawei.goodsshopping.adapter.MyBaseFragmentPagerAdapter;
import com.bawei.goodsshopping.vo.SortThird;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SubSortActivity extends AppCompatActivity {


    private String mBrand = "46";
    private String mCategory = "0";
    private String order_by = "5";  //默认价格排序
    private String pageSize = "30";


    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    private MyApplication myApplication = MyApplication.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_sort);

        myApplication.addActivity(this);
        /*
        接受Intent传过来的值
         */
        Intent intent = getIntent();
        SortThird sortThird = (SortThird) intent.getSerializableExtra("sortThird");

        String brand = sortThird.getBrand_id();
        String category = sortThird.getCategory_id();

        /*

         */
        setmBrand(brand);
        setmCategory(category);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        mViewPager = (ViewPager) findViewById(R.id.sub_sort_vPager);
        mTabLayout = (TabLayout) findViewById(R.id.sub_sort_tablayout);



        onVPager();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        finish();
        overridePendingTransition(R.anim.activity_zoom_in,R.anim.activity_zoom_out);
        return true;
    }
    private void onVPager() {

        String defaultHttp="http://platform.okbuy.com/app/v12/search/index?" +
                "instock=1&page_id=1&page_size="+pageSize+"&order_by=3&brand_id="+mBrand+"&category_id="+mCategory+"&field_hides=brand" +
                "&app_id=1034&os=android&session_id=867581023689715&site_mask=7&version=4.3.7";

        List<Fragment> fragmentList = new ArrayList<>();
        BlankFragment fragment1 = new BlankFragment(defaultHttp);
        defaultHttp="http://platform.okbuy.com/app/v12/search/index?" +
                "instock=1&page_id=1&page_size="+pageSize+"&order_by=2&brand_id="+mBrand+"&category_id="+mCategory+"&field_hides=brand" +
                "&app_id=1034&os=android&session_id=867581023689715&site_mask=7&version=4.3.7";
        BlankFragment fragment2 = new BlankFragment(defaultHttp);
        defaultHttp="http://platform.okbuy.com/app/v12/search/index?" +
                "instock=1&page_id=1&page_size="+pageSize+"&order_by=1&brand_id="+mBrand+"&category_id="+mCategory+"&field_hides=brand" +
                "&app_id=1034&os=android&session_id=867581023689715&site_mask=7&version=4.3.7";
        BlankFragment fragment3 = new BlankFragment(defaultHttp);
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
        String[] strTitle = new String[]{"销量","价格","最新"};
        MyBaseFragmentPagerAdapter adapter = new MyBaseFragmentPagerAdapter(getSupportFragmentManager(), fragmentList,strTitle);
        mViewPager.setAdapter(adapter);

        mTabLayout.setupWithViewPager(mViewPager);

    }

    public String getmBrand() {
        return mBrand;
    }

    public void setmBrand(String mBrand) {
        this.mBrand = mBrand;
    }

    public String getmCategory() {
        return mCategory;
    }

    public void setmCategory(String mCategory) {
        this.mCategory = mCategory;
    }

    public String getOrder_by() {
        return order_by;
    }

    public void setOrder_by(String order_by) {
        this.order_by = order_by;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
