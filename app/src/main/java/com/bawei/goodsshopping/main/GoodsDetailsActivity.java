package com.bawei.goodsshopping.main;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.goodsshopping.db.MyOpenHelper;
import com.bawei.goodsshopping.vo.SortDetailPhotos;
import com.bawei.goodsshopping.vo.SortDetails;
import com.bawei.goodsshopping.vo.SortDetailsResults;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 商品详情
 */

public class GoodsDetailsActivity extends AppCompatActivity implements View.OnClickListener
        ,CompoundButton.OnCheckedChangeListener{


    private MyOpenHelper mMyOpenHelper = new MyOpenHelper(GoodsDetailsActivity.this,"test.db",null,1);

    private Handler handler = new Handler();
    private ImageLoader imageLoader = ImageLoader.getInstance();

    private ViewPager mViewPager;

    private TextView mTextDetailName;
    private TextView mTextDetailSalePrice;
    private TextView mTextDetailMarketPrice;
    private ImageView mBtnDetailsBack;
    private ImageView mAddCartImg;
    private ImageView mCartImg;

    /*
    Banner
     */
    private ImageView[] imageViews = null;
    private ImageView imageView = null;
    private ViewPager advPager = null;
    private AtomicInteger what = new AtomicInteger(0);
    private boolean isContinue = true;
    ArrayList<View> advPics = new ArrayList<>();
    private CheckBox mCheckB;

    private MyApplication myApplication = MyApplication.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_details);

        myApplication.addActivity(this);


        mViewPager = (ViewPager) findViewById(R.id.goods_detail_vPager);
        mTextDetailName = (TextView) findViewById(R.id.goods_details_name);
        mTextDetailSalePrice = (TextView) findViewById(R.id.goods_details_salePrice);
        mTextDetailMarketPrice = (TextView) findViewById(R.id.goods_details_marketPrice);
        mBtnDetailsBack = (ImageView) findViewById(R.id.details_btn_back);
        mBtnDetailsBack.setOnClickListener(this);

        mCheckB = (CheckBox)findViewById(R.id.goods_details_shoucang);
        mCheckB.setOnCheckedChangeListener(this);

        mAddCartImg = (ImageView) findViewById(R.id.goods_details_addtocart);
        mCartImg = (ImageView) findViewById(R.id.goods_details_gouwuche);
        mCartImg.setOnClickListener(this);

        Intent intent = getIntent();
        //通过Intent传值
        String product_id = intent.getStringExtra("product_id");

        String HttpDetails = "http://platform.okbuy.com/app/v12/product/get_detail_infos/"+product_id+"?if_add_self_to_relate_coms=1";

        onHttpRequest(HttpDetails);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.details_fab);
        fab.hide();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                AlertDialog.Builder alert = new AlertDialog.Builder(GoodsDetailsActivity.this);
//                alert.setTitle("请选择")

            }
        });
    }

    private void onHttpRequest(String httpDetails) {

        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url(httpDetails)
                .build();

        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                /*
                Erro
                 */
                Log.i("info", "Erro");
            }

            @Override
            public void onResponse(Response response) throws IOException {

                ResponseBody body = response.body();

                String responseData = body.string();

                final List<String> detailsPics = onParseData(responseData);


                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        /*
                        显示信息
                         */
                        initViewPager(detailsPics);
                    }


                });

            }
        });


    }

    /**
     * 解析数据
     * @param data
     * @return
     */
    private List<String> onParseData(String data){

        Gson gson = new Gson();

        SortDetails sortDetails = gson.fromJson(data, SortDetails.class);

        SortDetailsResults sortDetailsResults = sortDetails.getResults();

        /*
        显示商品的详情
         */
        final String name = sortDetailsResults.getName(); //商品的名称
        final int product_id = sortDetailsResults.getProduct_id();    //商品的生产标识
        final String list_image = sortDetailsResults.getList_image(); //商品的图片URL
        final int sale_price = sortDetailsResults.getSale_price(); //商品的销售价格

        final int market_price = sortDetailsResults.getMarket_price(); //商品的参考价格
        int brand_id = sortDetailsResults.getBrand_id();   //商品品牌ID
        String brand_name = sortDetailsResults.getBrand_name();   //商品品牌名称
        int category_id = sortDetailsResults.getCategory_id();   //商品分类ID
        final String category_name = sortDetailsResults.getCategory_name();   //商品分类名称


        handler.post(new Runnable() {
            @Override
            public void run() {

                mTextDetailName.setText(name);
                mTextDetailSalePrice.setText("¥："+sale_price);
                mTextDetailMarketPrice.setText("¥:" + market_price);
                mTextDetailMarketPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);  //添加删除线


               /*
               添加到购物车
                */
                mAddCartImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        /*
                        添加到数据库
                         */
                        addSQlite(name,product_id,list_image,sale_price,market_price,category_name);

                        Snackbar.make(v,"添加成功",Snackbar.LENGTH_SHORT).show();

                    }
                });

            }
        });


        /*
        得到ViewPager展示图片的URL
         */
        String detailsPic0 = sortDetailsResults.getList_image();
        SortDetailPhotos sortDetailPhotos = sortDetailsResults.getPhotos();

        String detailsPic1 = sortDetailPhotos.getL_1();
        String detailsPic2 = sortDetailPhotos.getL_2();
        String detailsPic3 = sortDetailPhotos.getL_3();
        String detailsPic4 = sortDetailPhotos.getL_4();
        String detailsPic5 = sortDetailPhotos.getL_5();
        String detailsPic6 = sortDetailPhotos.getL_6();
        String detailsPic7 = sortDetailPhotos.getL_7();
        String detailsPic8 = sortDetailPhotos.getL_8();


        List<String> detailsPics = new ArrayList<>();
        if(detailsPic0!=null){
            detailsPics.add(detailsPic0);
        }

        if(detailsPic1!=null){
            detailsPics.add(detailsPic1);
        }
        if(detailsPic2!=null){
            detailsPics.add(detailsPic2);
        }
        if(detailsPic3!=null){
            detailsPics.add(detailsPic3);
        }
        if(detailsPic4!=null){
            detailsPics.add(detailsPic4);
        }if(detailsPic5!=null){
            detailsPics.add(detailsPic5);
        }if(detailsPic6!=null){
            detailsPics.add(detailsPic6);
        }
        if(detailsPic7!=null){
            detailsPics.add(detailsPic7);
        }
        if(detailsPic8!=null){
            detailsPics.add(detailsPic8);
        }

        return detailsPics;

    }

    /**
     * 添加到数据库
     * @param name
     * @param product_id
     * @param list_image
     * @param sale_price
     * @param market_price
     * @param category_name
     */
    private void addSQlite(String name, int product_id, String list_image, int sale_price, int market_price, String category_name) {

        SQLiteDatabase db = mMyOpenHelper.getWritableDatabase();

        db.execSQL("insert into goods( uname,product_id,list_image,sale_price,market_price,category_name) values(?,?,?,?,?,?)"
                ,new Object[]{ name , product_id , list_image , sale_price , market_price , category_name });

        db.close();//关闭

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        finish();
        overridePendingTransition(R.anim.activity_zoom_in, R.anim.activity_zoom_out);
        return true;
    }

    /**
     * Banner
     */
    private void initViewPager(List<String> detailsPics) {
        advPager = (ViewPager) findViewById(R.id.goods_detail_vPager);

        ViewGroup group = (ViewGroup) findViewById(R.id.viewGroup);

        ImageView imageView = null;
        for (int i=0;i<detailsPics.size();i++){
            imageView = new ImageView(this);
            imageLoader.displayImage(detailsPics.get(i), imageView);
            advPics.add(imageView);
        }

        // 对imageviews进行填充
        imageViews = new ImageView[advPics.size()];
        // 小图标
        for (int i = 0; i < advPics.size(); i++) {
            imageView = new ImageView(this);
            imageView.setLayoutParams(new AbsListView.LayoutParams(20, 20));
            imageView.setPadding(5, 5, 5, 5);
            imageViews[i] = imageView;
            if (i == 0) {
                imageViews[i].setBackgroundResource(R.mipmap.banner_bg1);
            } else {
                imageViews[i].setBackgroundResource(R.mipmap.banner_bg2);
            }
            group.addView(imageViews[i]);
        }

        /*
        ViewPager：setAdapter、setOnPageChangeListener、setOnClickListener
         */
        advPager.setAdapter(new AdvAdapter(advPics));
        advPager.setOnPageChangeListener(new GuidePageChangeListener());
        advPager.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(GoodsDetailsActivity.this, "图片放大效果", Toast.LENGTH_SHORT).show();
            }
        });
        advPager.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        isContinue = false;
                        break;
                    case MotionEvent.ACTION_UP:
                        isContinue = true;
                        break;
                    default:
                        isContinue = true;
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        switch(id){

            case R.id.details_btn_back: //从详情页面返回

                //结束当前Activity
                GoodsDetailsActivity.this.finish();

                break;


            case R.id.goods_details_gouwuche:   //跳转到购物车

                GoodsDetailsActivity.this.finish();

                break;

        }

    }
    /**
     * CheckBox 选中收藏
     * @param buttonView
     * @param isChecked
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if(isChecked){

//            ArrayAdapter
            /*
            添加到数据库
             */
            Snackbar.make(buttonView,"已收藏",Snackbar.LENGTH_SHORT).show();

        }else{
            /*
            查询，删除
             */
            Snackbar.make(buttonView,"已取消收藏",Snackbar.LENGTH_SHORT).show();

        }
    }

    /**
     * 监听Page滑动
     */
    private final class GuidePageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int arg0) {
            //Message中一种属性，可以保存某一种特殊的值，what去保存
            what.getAndSet(arg0);
            for (int i = 0; i < imageViews.length; i++) {
                imageViews[arg0].setBackgroundResource
                        //设置选中要显示的图标
                        (R.mipmap.banner_bg1);
                if (arg0 != i) {
                    imageViews[i].setBackgroundResource
                        //设置未选中的显示图标
                            (R.mipmap.banner_bg2);
                }
            }

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

    }

    /**
     * Pager适配器
     */
    private class AdvAdapter extends PagerAdapter {

        //当前adapter所实现的功能，是进行图片的切换
        private List<View> views = null;

        public AdvAdapter(List<View> views) {
            this.views = views;
        }


        @Override
        public void finishUpdate(View arg0) {

        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            container.addView(views.get(position));
            return views.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView(views.get(position));
        }


        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;//google公司要求我们必须要这样写
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {

        }

    }

}
