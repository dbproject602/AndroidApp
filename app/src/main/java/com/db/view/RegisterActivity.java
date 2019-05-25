package com.db.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.db.viewmodel.LoginViewModel;
import com.db.viewmodel.RegisterViewModel;
import com.example.activity.R;

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
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.open,
                        R.anim.close);
                finish();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading.setVisibility(View.VISIBLE);
                int userId = 0; //userId无效
                String userName = username.getText().toString();
                String Name = name.getText().toString();
                String Telephone = telephone.getText().toString();
                String Password = password.getText().toString();
                String Address = address.getText().toString();
                try {
                    registerViewModel.setUserBean(userId,userName,Password,Telephone,Address,Name);
                    registerViewModel.register();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
