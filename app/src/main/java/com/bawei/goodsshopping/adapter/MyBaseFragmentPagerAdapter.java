package com.bawei.goodsshopping.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/15.
 *
 *基于FragmentPagerAdapter
 */
public  class MyBaseFragmentPagerAdapter extends FragmentPagerAdapter{

    private List<Fragment> mList;  //存储Fragment的集合
    private String[] mTitle;    //存储Title数组

    public MyBaseFragmentPagerAdapter(FragmentManager fm , List<Fragment> fragmentArrayList , String[] mTitle) {
        super(fm);

        this.mList = fragmentArrayList;
        this.mTitle = mTitle;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        String title = mTitle[position];

        if(title == null || title == ""){

            return "null";
        }

        return title;
    }

    @Override
    public Fragment getItem(int position) {

        return mList.get(position);
    }

    @Override
    public int getCount() {


        return mList.size();
    }
}
