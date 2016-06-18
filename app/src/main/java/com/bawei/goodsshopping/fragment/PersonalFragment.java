package com.bawei.goodsshopping.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.bawei.goodsshopping.adapter.PersonAdapter;
import com.bawei.goodsshopping.main.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 *
 * 个人中心
 */
public class PersonalFragment extends Fragment {


    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ImageView mUserImg;
    List<Fragment> fragmentList;
    public PersonalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_personal, container, false);


        mTabLayout = (TabLayout) inflate.findViewById(R.id.id_fragment_person_tablayout);
        mViewPager = (ViewPager) inflate.findViewById(R.id.id_fragment_person_viewPager);


        fragmentList= new ArrayList<>();

        PersonSubLoginFragment loginFragment = new PersonSubLoginFragment();
        PersonSubRegisterFragment registerFragment = new PersonSubRegisterFragment();

        fragmentList.add(loginFragment);
        fragmentList.add(registerFragment);


        MyPagerAdapter adapter = new MyPagerAdapter(getActivity().getSupportFragmentManager());
        mViewPager.setAdapter(adapter);

        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(adapter);


        return inflate;
    }

    class MyPagerAdapter extends FragmentPagerAdapter{

        @Override
        public CharSequence getPageTitle(int position) {

            String[] title = new String[]{"登陆","注册"};
            String titlePage = title[position];

            if(titlePage == null || "".equals(titlePage)){
                titlePage = "当前为空";
            }
            return titlePage;

        }

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }


        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }

    /**
     * 重新计算ListView的高度，解决ScrollView和ListView两个View都有滚动的效果，在嵌套使用时起冲突的问题
     * @param listView
     */
    public void setListViewHeight(ListView listView) {

        // 获取ListView对应的Adapter

        ListAdapter listAdapter = listView.getAdapter();

        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0); // 计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }


}
