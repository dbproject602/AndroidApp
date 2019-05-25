package com.db.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.db.viewmodel.LoginViewModel;
import com.example.activity.R;

import bean.UserBean;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button loginbtn = findViewById(R.id.login);
        final EditText accountview = findViewById(R.id.username);
        final EditText passwordview = findViewById(R.id.password);
        final ProgressBar progressBar = findViewById(R.id.loading);
        final LoginViewModel model = ViewModelProviders.of(this).get(LoginViewModel.class);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    model.login(accountview.getText().toString(),passwordview.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                progressBar.setVisibility(View.VISIBLE);
            }
        });
        final Observer<UserBean> userBeanObserver = new Observer<UserBean>() {
            @Override
            public void onChanged(@Nullable UserBean userinfoBean) {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                progressBar.setVisibility(View.GONE);
                startActivity(intent);
                finish();
            }
        };
        model.getUser().observe(this,userBeanObserver);
    }

}
