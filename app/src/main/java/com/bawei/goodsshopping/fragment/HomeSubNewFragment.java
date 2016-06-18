package com.bawei.goodsshopping.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.goodsshopping.main.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeSubNewFragment extends Fragment {


    public HomeSubNewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_sub_new, container, false);
    }


}
