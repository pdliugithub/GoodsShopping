package com.bawei.goodsshopping.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.bawei.goodsshopping.adapter.MyBaseFragmentPagerAdapter;
import com.bawei.goodsshopping.main.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 *
 * 分类
 */
public class SortsFragment extends Fragment {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    private RadioGroup mRadioGroup;

    private ArrayList<android.support.v4.app.Fragment> mFragmentList;
    private String[] mPagerTitle;
    private Context mContext;

    private FragmentManager mFragmentManager;
    private MyBaseFragmentPagerAdapter mPagerAdapter;

    public SortsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sorts, container, false);

        mViewPager = (ViewPager) view.findViewById(R.id.id_fragment_home_viewPager);
        mTabLayout = (TabLayout) view.findViewById(R.id.id_fragment_home_tabLayout);

        HomeSubHotFragment mHomeSubHotFragment=new HomeSubHotFragment();
        HomeSubNewFragment mHomeSubNewFragment=new HomeSubNewFragment();
        HomeSubGoodsTuiJian mHomeSubGoodsTuiJian = new HomeSubGoodsTuiJian();

        mFragmentList = new ArrayList<Fragment>();
        mPagerTitle = new String[]{"商品分类","综合商城","新品"};
        mFragmentList.add(mHomeSubGoodsTuiJian);
        mFragmentList.add(mHomeSubHotFragment);
        mFragmentList.add(mHomeSubNewFragment);

        mPagerAdapter = new MyBaseFragmentPagerAdapter(getActivity().getSupportFragmentManager() , mFragmentList , mPagerTitle);
        mViewPager.setAdapter(mPagerAdapter);

        mTabLayout.setupWithViewPager(mViewPager);

        return view;
    }


}
