package com.bawei.goodsshopping.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.bawei.goodsshopping.adapter.HomeListAdapter;
import com.bawei.goodsshopping.main.R;
import com.bawei.goodsshopping.main.WebViewActivity;
import com.bawei.goodsshopping.main.ZxingActivity;
import com.bawei.goodsshopping.vo.HomeListParse;
import com.bawei.goodsshopping.vo.InfoList;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

/**
 * A simple
 *
 * 首页
 */
public class HomeFragment extends Fragment implements OnClickListener,SwipeRefreshLayout.OnRefreshListener{

    private String defaultStr = "http://apk.gou.gionee.com/api/apk_story/listEXT?cat_id=13&uid=fcdf90bad9454c94a400f3ba2411e935&page=1&perpage=6&version=1";
    private String[] url = new String[]{
            "http://s.gou.gionee.com/click/redirect?t=4270213745"
            ,"http://s.gou.gionee.com/click/redirect?t=2214708493"
            ,"http://s.gou.gionee.com/click/redirect?t=3365180603"
            ,"http://s.gou.gionee.com/click/redirect?t=1894701335"
            ,"http://s.gou.gionee.com/click/redirect?t=785341640"
    };
    private ImageView[] imageViews = null;
    private ImageView imageView = null;
    private GridView mGridView = null;
    private ViewPager advPager = null;
    private  List<InfoList> infoLists;
    private AtomicInteger what = new AtomicInteger(0);
    private boolean isContinue = true;
    private HomeListAdapter mHomeListAdapter;
    private Handler handler =new Handler();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ImageView mHuaFei,mZhe,mHuoDao,mTangou,mLuyou;

    public HomeFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.id_home_swipeRefreshLayout);
        mSwipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorGreen, R.color.colorPrimaryDark, R.color.colorYellow);
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.GRAY);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mGridView = (GridView) view.findViewById(R.id.fragment_home_listView);

        mHuaFei = (ImageView) view.findViewById(R.id.fragment_home_img_huafei);
        mHuaFei.setOnClickListener(this);
        mZhe = (ImageView) view.findViewById(R.id.fragment_home_img_zhe);
        mZhe.setOnClickListener(this);
        mHuoDao = (ImageView) view.findViewById(R.id.fragment_home_img_huodaofukuan);
        mHuoDao.setOnClickListener(this);
        mTangou = (ImageView) view.findViewById(R.id.fragment_home_img_tuangou);
        mTangou.setOnClickListener(this);
        mLuyou = (ImageView) view.findViewById(R.id.fragment_home_img_luyou);
        mLuyou.setOnClickListener(this);

        ImageButton zxing = (ImageButton) view.findViewById(R.id.id_fragment_home_imgBtn_zxing);

        zxing.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), ZxingActivity.class);
                startActivity(intent);

            }
        });

        initViewPager(view);


        /*
         *ListView
         */
        setListViewAdapter();

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(infoLists != null){

                    String url = infoLists.get(position).getUrl();

                    onIntentWeb(url);
                }

            }
        });



        return view;
    }

    private void setListViewAdapter() {


        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url(defaultStr)
                .build();

        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {

                String parseData = response.body().string();

                infoLists  = onParseData(parseData);

                /*
                主线程中更新Adapter
                 */
                handler.post(new Runnable() {
                    @Override
                    public void run() {


                        mHomeListAdapter = new HomeListAdapter(infoLists,getActivity());
                        mGridView.setAdapter(mHomeListAdapter);

                    }
                });


            }
        });

    }


    private  List<InfoList> onParseData(String parse){

        Gson gson = new Gson();

        HomeListParse homeListParse = gson.fromJson(parse, HomeListParse.class);

        List<InfoList> infoList = homeListParse.getData().getList();

        return infoList;

    }
    /**
     * ViewPagerBanner
     */
    private void initViewPager(View view) {
        advPager = (ViewPager)view.findViewById(R.id.adv_pager);
        ViewGroup group = (ViewGroup)view. findViewById(R.id.viewGroup);

        // 这里存放的是四张广告背景
        List<View> advPics = new ArrayList<View>();
        int[] imgId = new int[]{
                R.mipmap.banner1
                ,R.mipmap.banner2
                ,R.mipmap.banner3
                ,R.mipmap.banner4
                ,R.mipmap.banner5
                };
        ImageView imageView = null;
        for(int i=0;i < imgId.length; i++){

//            TabLayout
            imageView = new ImageView(getActivity());
            imageView.setBackgroundResource(imgId[i]);

            advPics.add(imageView);
        }


        // 对imageviews进行填充
        imageViews = new ImageView[advPics.size()];
        // 小图标
        for (int i = 0; i < advPics.size(); i++) {
            imageView = new ImageView(getActivity());
            imageView.setLayoutParams(new LayoutParams(20, 20));
            imageView.setPadding(5, 5, 5, 5);
            imageViews[i] = imageView;
            if (i == 0) {
                imageViews[i].setBackgroundResource(R.mipmap.banner_bg1);
            } else {
                imageViews[i].setBackgroundResource(R.mipmap.banner_bg2);
            }
            group.addView(imageViews[i]);
        }

        advPager.setAdapter(new AdvAdapter(advPics));
        advPager.setOnPageChangeListener(new GuidePageChangeListener());
        advPager.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "id"+v.getId(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("link","");
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.activity_zoom_in,R.anim.activity_zoom_out);

            }
        });
        advPager.setOnTouchListener(new OnTouchListener() {

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
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    if (isContinue) {
                        viewHandler.sendEmptyMessage(what.get());
                        whatOption();
                    }
                }
            }

        }).start();
    }

    private void whatOption() {
        what.incrementAndGet();
        if (what.get() > imageViews.length - 1) {
            what.getAndAdd(-5);
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {

        }
    }

    private final android.os.Handler viewHandler = new android.os.Handler() {

        @Override
        public void handleMessage(Message msg) {

            super.handleMessage(msg);
            advPager.setCurrentItem(msg.what);
        }

    };

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id){

            case R.id.fragment_home_img_huafei:
                onIntentWeb("http://s.gou.gionee.com/click/redirect?t=3962014238");
                break;
            case R.id.fragment_home_img_zhe:
                onIntentWeb("http://s.gou.gionee.com/click/redirect?t=3447829998");
                break;
            case R.id.fragment_home_img_huodaofukuan:
                onIntentWeb("http://s.gou.gionee.com/click/redirect?t=2196996593");
                break;case R.id.fragment_home_img_tuangou:
                onIntentWeb("http://s.gou.gionee.com/click/redirect?t=76595041");
                break;
            case R.id.fragment_home_img_luyou:
                onIntentWeb("http://s.gou.gionee.com/click/redirect?t=2168452614");
                break;



        }
    }


    @Override
    public void onRefresh() {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {


                setListViewAdapter();

                if(mSwipeRefreshLayout.isRefreshing()){

                    mSwipeRefreshLayout.setRefreshing(false);


                }

            }
        } , 3000);

    }

    private final class GuidePageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int arg0) {
            //Message中一种属性，可以保存某一种特殊的值，what去保存
            what.getAndSet(arg0);
            for (int i = 0; i < imageViews.length; i++) {
                imageViews[arg0].setBackgroundResource(R.mipmap.banner_bg1);
                if (arg0 != i) {
                    imageViews[i].setBackgroundResource(R.mipmap.banner_bg2);
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

    private final class AdvAdapter extends PagerAdapter {

        //当前adapter所实现的功能，是进行图片的切换
        private List<View> views = null;

        public AdvAdapter(List<View> views) {
            this.views = views;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(views.get(arg1));
        }

        @Override
        public void finishUpdate(View arg0) {

        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public Object instantiateItem(View arg0, final int arg1) {
            ((ViewPager) arg0).addView(views.get(arg1), 0);

            views.get(arg1).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    switch (arg1) {

                        case 0:
                            onIntentWeb(url[0]);
                            break;
                        case 1:
                            onIntentWeb(url[1]);
                            break;
                        case 2:
                            onIntentWeb(url[2]);
                            break;
                        case 3:
                            onIntentWeb(url[3]);
                            break;
                        case 4:
                            onIntentWeb(url[4]);
                            break;



                    }
                }
            });

            return views.get(arg1);
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

    private  void onIntentWeb(String strUrl){

            Intent intent = new Intent(getActivity(),WebViewActivity.class);
            intent.putExtra("link",strUrl);
            startActivity(intent);

    }



}
