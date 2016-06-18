package com.bawei.goodsshopping.main;

import android.app.Activity;
import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/15.
 */
public class MyApplication extends Application {


    /**
     * Activity列表
     */
    private List<Activity> activityList = new LinkedList<>();

    /**
     * 全局唯一实例
     */
    private static MyApplication instance;

    /**
     * 该类采用单例模式，不能实例化
     */
//    private MyApplication()
//    {
//    }

    @Override
    public void onCreate() {
        super.onCreate();


        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //**注意该方法要再setContentView方法之前实现
        //注意：在SDK各功能组件使用之前都需要调用
        //SDKInitializer.initialize(getApplicationContext());，因此我们建议该方法放在Application的初始化方法中
        SDKInitializer.initialize(getApplicationContext());

        File cacheDir = StorageUtils.getCacheDirectory(getApplicationContext());
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .memoryCacheExtraOptions(480, 800) // default = device screen dimensions
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024)
                .memoryCacheSizePercentage(13) // default
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileCount(100)
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
                .imageDownloader(new BaseImageDownloader(getApplicationContext())) // default
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
                .writeDebugLogs()
                .build();

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);

    }

    /**
     * 获取类实例对象
     * @return    MyActivityManager
     */
    public static MyApplication getInstance() {
        if (null == instance) {
            instance = new MyApplication();
        }
        return instance;
    }


    /**
     * 保存Activity到现有列表中
     * @param activity
     */
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }


    /**
     * 关闭保存的Activity
     */
    public void exit() {
        if(activityList!=null)
        {
            Activity activity;

            for (int i=0; i<activityList.size(); i++) {
                activity = activityList.get(i);

                if(activity!=null)
                {
                    if(!activity.isFinishing())
                    {
                        activity.finish();
                    }

                    activity = null;
                }

                activityList.remove(i);
                i--;
            }
        }
    }
}
