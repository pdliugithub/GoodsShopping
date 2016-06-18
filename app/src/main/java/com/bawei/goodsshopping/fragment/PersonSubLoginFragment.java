package com.bawei.goodsshopping.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bawei.goodsshopping.main.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonSubLoginFragment extends Fragment {

    private EditText userName;
    private EditText userPassword;
    private TextInputLayout userNameWrapper;
    private TextInputLayout userPasswordWrapper;

    public PersonSubLoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_person_sub_login, container, false);

        userNameWrapper = (TextInputLayout) view.findViewById(R.id.userNameWrapper);
        userPasswordWrapper = (TextInputLayout) view.findViewById(R.id.userPasswordWrapper);

        userName = (EditText) view.findViewById(R.id.userName);
        userPassword = (EditText) view.findViewById(R.id.userName);


        Button btnLogin = (Button) view.findViewById(R.id.id_btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                收起软键盘
                 */
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

                if(imm != null){
                    imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(),0);
                }

                //读取数据
                SharedPreferences spf = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                String usename = spf.getString("userName", "pd");
                String userpassword = spf.getString("userPassword", "xkrossia");

                String name = userName.getText().toString().trim();
                String password = userPassword.getText().toString().trim();


                if(!usename.equals(name)){
                    userName.setError("用户名不存在");
                    return;
                }

                if(!userpassword.equals(password)){
                    userPassword.setError("密码不正确");
                    return;
                }

                Toast.makeText(getActivity(),"登陆成功" + usename, Toast.LENGTH_SHORT).show();



            }
        });

        return view;
    }

    private void onSharef() {




    }

}
