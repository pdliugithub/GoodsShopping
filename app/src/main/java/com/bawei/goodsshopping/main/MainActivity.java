package com.bawei.goodsshopping.main;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bawei.goodsshopping.fragment.HomeFragment;
import com.bawei.goodsshopping.fragment.KnowThingFragment;
import com.bawei.goodsshopping.fragment.PersonalFragment;
import com.bawei.goodsshopping.fragment.SortsFragment;
import com.bawei.goodsshopping.mybase.MyHttpThread;


public class MainActivity extends AppCompatActivity implements View.OnClickListener , NavigationView.OnNavigationItemSelectedListener{

    private ImageView mFoyerBtn = null;
    private ImageView mSortBtn = null;
    private ImageView mKnowThingBtn = null;
    private ImageView mPersonalBtn = null;

    private FrameLayout mMainFram = null;
    private FragmentManager mFragmentManager = null;

    /*
    Fragment
     */
    private HomeFragment homeFragment = null;
    private PersonalFragment personalFragment = null;
    private SortsFragment sortsFragment = null;
    private KnowThingFragment knowThingFragment = null;

    private MyApplication myApplication = MyApplication.getInstance();
    private Button mBtnExit = null;    //一键退出

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myApplication.addActivity(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navi = (NavigationView) findViewById(R.id.navi);
        navi.setNavigationItemSelectedListener(this);


        /*
        进行网络状态判断
         */
        boolean state = onHttpState(this);
        if(state){
            Toast.makeText(MainActivity.this, "网络状态良好", Toast.LENGTH_SHORT).show();
        }
        if(!state){
            Toast.makeText(MainActivity.this, "网络堵塞。。。", Toast.LENGTH_SHORT).show();
        }

        /*
        Layout、View
         */
        mMainFram = (FrameLayout) findViewById(R.id.id_main_fram_layout);
        mBtnExit = (Button) findViewById(R.id.btn_exit);
        mBtnExit.setOnClickListener(this);

        mFoyerBtn = (ImageView) findViewById(R.id.id_main_btn_foyer);
        mFoyerBtn.setOnClickListener(this);
        mSortBtn = (ImageView) findViewById(R.id.id_main_btn_sort);
        mSortBtn.setOnClickListener(this);
        mKnowThingBtn = (ImageView) findViewById(R.id.id_main_btn_knowThing);
        mKnowThingBtn.setOnClickListener(this);
        mPersonalBtn = (ImageView) findViewById(R.id.id_main_btn_personal);
        mPersonalBtn.setOnClickListener(this);

        mFragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        homeFragment = new HomeFragment();
        personalFragment = new PersonalFragment();
        sortsFragment = new SortsFragment();
        knowThingFragment = new KnowThingFragment();

        fragmentTransaction.add(R.id.id_main_fram_layout,homeFragment,"home");
        fragmentTransaction.add(R.id.id_main_fram_layout,personalFragment,"personal");
        fragmentTransaction.add(R.id.id_main_fram_layout,sortsFragment,"sort");
        fragmentTransaction.add(R.id.id_main_fram_layout,knowThingFragment,"knowThing");
        fragmentTransaction.commit();

        /*
        切换Fragment按钮
         */
        switchBtnInit("home");

        /*
        切换Button背景
         */
        switchBtnBgInit("home");

    }

    /**
     * switch button Background
     * @param home
     */
    public void switchBtnBgInit(String home) {

        if("home".equals(home)){

            mFoyerBtn.setImageResource(R.mipmap.main_bottom_tab_home_focus);
            mSortBtn.setImageResource(R.mipmap.main_bottom_tab_category_normal);
            mKnowThingBtn.setImageResource(R.mipmap.main_bottom_tab_cart_normal);
            mPersonalBtn.setImageResource(R.mipmap.main_bottom_tab_personal_normal);


        }else if("sort".equals(home)) {

            mFoyerBtn.setImageResource(R.mipmap.main_bottom_tab_home_normal);
            mSortBtn.setImageResource(R.mipmap.main_bottom_tab_category_focus);
            mKnowThingBtn.setImageResource(R.mipmap.main_bottom_tab_cart_normal);
            mPersonalBtn.setImageResource(R.mipmap.main_bottom_tab_personal_normal);
        }else if("knowThing".equals(home)) {

            mFoyerBtn.setImageResource(R.mipmap.main_bottom_tab_home_normal);
            mSortBtn.setImageResource(R.mipmap.main_bottom_tab_category_normal);
            mKnowThingBtn.setImageResource(R.mipmap.main_bottom_tab_cart_focus);
            mPersonalBtn.setImageResource(R.mipmap.main_bottom_tab_personal_normal);
        }else if("personal".equals(home)) {

            mFoyerBtn.setImageResource(R.mipmap.main_bottom_tab_home_normal);
            mSortBtn.setImageResource(R.mipmap.main_bottom_tab_category_normal);
            mKnowThingBtn.setImageResource(R.mipmap.main_bottom_tab_cart_normal);
            mPersonalBtn.setImageResource(R.mipmap.main_bottom_tab_personal_focus);

        }else if("default".equals(home)) {

            mFoyerBtn.setImageResource(R.mipmap.main_bottom_tab_home_normal);
            mSortBtn.setImageResource(R.mipmap.main_bottom_tab_category_normal);
            mKnowThingBtn.setImageResource(R.mipmap.main_bottom_tab_category_normal);
            mPersonalBtn.setImageResource(R.mipmap.main_bottom_tab_personal_normal);

        }

    }

    /**
     * 切换
     * @param home
     */
    public  void switchBtnInit(String home) {

        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        if("home".equals(home)){

            transaction.show(homeFragment);
            transaction.hide(sortsFragment);
            transaction.hide(knowThingFragment);
            transaction.hide(personalFragment);
            transaction.commit();

        }else if("sort".equals(home)){

            transaction.show(sortsFragment);
            transaction.hide(homeFragment);
            transaction.hide(knowThingFragment);
            transaction.hide(personalFragment);
            transaction.commit();
        }else if("knowThing".equals(home)){

            transaction.show(knowThingFragment);
            transaction.hide(sortsFragment);
            transaction.hide(homeFragment);
            transaction.hide(personalFragment);
            transaction.commit();
        }else if("personal".equals(home)){

            transaction.show(personalFragment);
            transaction.hide(sortsFragment);
            transaction.hide(knowThingFragment);
            transaction.hide(homeFragment);
            transaction.commit();

        }


    }

    /**
     * Press back
     */
    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    /**
     * OptionsMenu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * OptionsItemSelected
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * 实现连续两次，退出APP
     * @param keyCode
     * @param event
     * @return
     */
    private long firstTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch(keyCode)
        {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {                                         //如果两次按键时间间隔大于2秒，则不退出
                    Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    firstTime = secondTime;//更新firstTime
                    return true;
                } else {                                                    //两次按键小于2秒时，退出应用
                    myApplication.exit();   //退出程序
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

    /**
     * Tabs Click listener
     * @param v
     */
    @Override
    public void onClick(View v) {

        int id = v.getId();

        switch (id){

            case R.id.id_main_btn_foyer:    //主页

                switchBtnInit("home");
                switchBtnBgInit("home");
                break;
            case R.id.id_main_btn_sort:     //分类

                switchBtnInit("sort");
                switchBtnBgInit("sort");
                break;
            case R.id.id_main_btn_knowThing:    //购物车

                switchBtnInit("knowThing");
                switchBtnBgInit("knowThing");
                break;
            case R.id.id_main_btn_personal:     //个人中心

                switchBtnInit("personal");
                switchBtnBgInit("personal");
                break;


            case R.id.btn_exit: //一键退出

                //透明AlertDialog
                 onAlertAlpha();


                break;

        }

    }

    /**
     * AlertDialog
     */
    private void onAlertAlpha() {

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("主人I miss you")
                .setIcon(R.mipmap.shake_fail_icon)
                .setMessage("你确定要退出应用")
                .setNegativeButton("等一分钟", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })

                .setPositiveButton("去意已决", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myApplication.exit();//退出
                    }
                })
                .show();


        Window window = dialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.alpha=0.8f;
        window.setAttributes(params);
    }

    /**
     * Navigation item  select Listener
     * @param menuItem
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {

        int itemId = menuItem.getItemId();
        switch (itemId){

            case R.id.nav_camara://主页

                switchBtnInit("home");

                switchBtnBgInit("home");

                break;
            case R.id.nav_gallery: //分类

                switchBtnInit("sort");

                switchBtnBgInit("sort");
                break;
            case R.id.nav_slideshow:     //知物\购物车
                switchBtnInit("knowThing");

                switchBtnBgInit("knowThing");
                break;
            case R.id.nav_manage:   //个人中心

                switchBtnInit("personal");
                switchBtnBgInit("personal");

                break;

            case R.id.nav_map:  //百度地图

                Intent intent = new Intent(MainActivity.this,MapActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_zoom_in, R.anim.activity_zoom_out);
                break;

            case R.id.nav_zxing:    //二维码

                Intent zxing = new Intent(MainActivity.this,ZxingActivity.class);
                startActivity(zxing);
                overridePendingTransition(R.anim.activity_zoom_in, R.anim.activity_zoom_out);

                break;


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 网络状态判断
     */
    public boolean onHttpState(Context context){


        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;


    }

}
