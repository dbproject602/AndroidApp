package com.db.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.db.viewmodel.AccountPageViewModel;
import com.db.viewmodel.RegisterViewModel;
import com.db.viewmodel.SafeSettingViewModel;
import com.example.activity.R;

import util.IOUtil;

public class SafeSettingActivity extends AppCompatActivity {
    private ImageButton backbtn;
    private EditText name;
    private EditText address;
    private EditText telephone;
    private EditText oldpassword;
    private EditText password;
    private EditText repassword;
    private Button register;
    private ProgressBar loading;
    private SafeSettingViewModel safeSettingViewModel;
    private String FILENAME = "userBean.dat";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_setting);
        backbtn = (ImageButton) findViewById(R.id.backbtn);
        name = (EditText) findViewById(R.id.name);
        address = (EditText) findViewById(R.id.address);
        telephone = (EditText) findViewById(R.id.telephone);
        oldpassword = (EditText) findViewById(R.id.oldpassword);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        register = (Button) findViewById(R.id.register);
        loading = (ProgressBar) findViewById(R.id.loading);
        final SafeSettingViewModel safeSettingViewModel = ViewModelProviders.of(this).get(SafeSettingViewModel.class);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_out,R.anim.slide_in);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString();
                String Telephone = telephone.getText().toString();
                String Password = password.getText().toString();
                String Repassword = repassword.getText().toString();
                String Address = address.getText().toString();
                try {
                    loading.setVisibility(View.VISIBLE);
                    safeSettingViewModel.setUserBean(Password,Telephone,Address,Name);
                    safeSettingViewModel.update();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        final Observer<Integer> flagObserver = new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer flag) {
                if(flag != 0) {
                    AccountPageViewModel.setUserBean(safeSettingViewModel.getUserBean().getValue());
                    try {
                        IOUtil.writeFileDataTobytes(FILENAME,safeSettingViewModel.getUserBean().getValue(),SafeSettingActivity.this);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    finish();
                    overridePendingTransition(R.anim.slide_out,R.anim.slide_in);
                }else{
                    safeSettingViewModel.setUserBean(AccountPageViewModel.getUserBean());
                    loading.setVisibility(View.GONE);
                    System.out.println("error");
                }
            }
        };
        safeSettingViewModel.getFlag().observe(this,flagObserver);

    }
}
