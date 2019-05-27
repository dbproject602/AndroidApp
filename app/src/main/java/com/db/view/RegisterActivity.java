package com.db.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.db.viewmodel.RegisterViewModel;
import com.example.activity.R;

import bean.UserBean;

public class RegisterActivity extends AppCompatActivity {
    private ImageButton backbtn;
    private EditText name;
    private EditText address;
    private EditText telephone;
    private EditText username;
    private EditText password;
    private EditText repassword;
    private Button register;
    private ProgressBar loading;
    private RegisterViewModel registerViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        backbtn = (ImageButton) findViewById(R.id.backbtn);
        name = (EditText) findViewById(R.id.name);
        address = (EditText) findViewById(R.id.address);
        telephone = (EditText) findViewById(R.id.telephone);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        register = (Button) findViewById(R.id.register);
        loading = (ProgressBar) findViewById(R.id.loading);
        registerViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_out,
                        R.anim.slide_in);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = username.getText().toString();
                String Name = name.getText().toString();
                String Telephone = telephone.getText().toString();
                String Password = password.getText().toString();
                String Repassword = repassword.getText().toString();
                String Address = address.getText().toString();
                if(!Password.equals(Repassword)||userName.equals("")||Name.equals("")||Telephone.equals("")||Password.equals("")||address.equals("")){
                    System.out.println("error");
                    return;
                }
                try {
                    loading.setVisibility(View.VISIBLE);
                    registerViewModel.setUserBean(userName,Password,Telephone,Address,Name);
                    registerViewModel.register();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        final Observer<Integer> flagObserver = new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer flag) {
                if(flag != 0) {
                    startLogin();
                }else{
                    loading.setVisibility(View.GONE);
                    System.out.println("error");
                }
            }
        };
        registerViewModel.getFlag().observe(this,flagObserver);

    }
    public void startLogin(){
        loading.setVisibility(View.GONE);
        finish();
        overridePendingTransition(R.anim.slide_out,
                R.anim.slide_in);
    }

}
