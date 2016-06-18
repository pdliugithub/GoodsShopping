package com.bawei.goodsshopping.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
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
public class PersonSubRegisterFragment extends Fragment {


    public PersonSubRegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_person_sub_register, container, false);

        Button btnRegister = (Button) view.findViewById(R.id.id_btn_register);
        final EditText userPwdEdit = (EditText) view.findViewById(R.id.userPassword_register);
        final EditText userNameEdit = (EditText) view.findViewById(R.id.userName_register);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 /*
                收起软键盘
                 */
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

                if (imm != null) {
                    imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
                }

                String userPwd = userPwdEdit.getText().toString().trim();
                String userName = userNameEdit.getText().toString().trim();


                SharedPreferences spf = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = spf.edit();
                editor.putString("userName", userName);
                editor.putString("userPassword", userPwd);
                editor.commit();

                userPwdEdit.setText("");
                userNameEdit.setText("");
                Snackbar.make(v, "注册成功", Snackbar.LENGTH_SHORT).show();

            }


        });


        return view;
    }

}
