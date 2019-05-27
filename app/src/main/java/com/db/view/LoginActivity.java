package com.db.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.db.viewmodel.AccountPageViewModel;
import com.db.viewmodel.LoginViewModel;
import com.db.viewmodel.SafeSettingViewModel;
import com.example.activity.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import util.IOUtil;
import util.ObjToBytes;

import bean.UserBean;

public class LoginActivity extends AppCompatActivity {
    private EditText userNameview;
    private EditText passwordview;
    private ProgressBar progressBar;
    private LoginViewModel loginViewModel;
    private Button loginbtn;
    private Button registerbtn;
    private String FILENAME = "userBean.dat";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.slide_out,
                R.anim.slide_in);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginbtn = findViewById(R.id.login);
        registerbtn = findViewById(R.id.register);
        userNameview = findViewById(R.id.username);
        passwordview = findViewById(R.id.password);
        progressBar = findViewById(R.id.loading);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        try {
            Object obj = IOUtil.readFileDataToObj(FILENAME,this);
            if (obj!=null){
                UserBean userBean = (UserBean) obj;
                AccountPageViewModel.setUserBean(userBean);
                SafeSettingViewModel.setUserBean(userBean);
                startMain();
            }else{
                System.out.println("obj is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                try {
                    loginViewModel.login(userNameview.getText().toString(),passwordview.getText().toString());
                //    startMain();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out,
                        R.anim.slide_in);
            }
        });
        final Observer<UserBean> userBeanObserver = new Observer<UserBean>() {
            @Override
            public void onChanged(@Nullable UserBean userBean) {
                if(userBean==null){
                    System.out.println();
                    progressBar.setVisibility(View.GONE);
                }else {
                    AccountPageViewModel.setUserBean(userBean);
                    SafeSettingViewModel.setUserBean(userBean);
                    startMain();
                }
            }
        };
        loginViewModel.getUserBean().observe(this,userBeanObserver);
    }
    public void startMain(){
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        progressBar.setVisibility(View.GONE);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_out,
                R.anim.fade_in);
        finish();
    }
}
