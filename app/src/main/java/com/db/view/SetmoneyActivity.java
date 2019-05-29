package com.db.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.db.viewmodel.AccountPageViewModel;
import com.db.viewmodel.RegisterViewModel;
import com.db.viewmodel.SafeSettingViewModel;
import com.example.activity.R;

import bean.UserBean;
import util.IOUtil;

public class SetmoneyActivity extends AppCompatActivity {
    private ImageButton backbtn;
    private EditText charge_moneny;
    private EditText password;
    private Button register;
    private ProgressBar loading;
    private String FILENAME = "userBean.dat";
    //    private RegisterViewModel registerViewModel;
    private UserBean curUserBean;
//    final SafeSettingViewModel safeSettingViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge_money);
        backbtn = (ImageButton) this.findViewById(R.id.backbtn);
        charge_moneny = (EditText) this.findViewById(R.id.name);
        password = (EditText) this.findViewById(R.id.password);
        register = (Button) this.findViewById(R.id.register);
        loading = (ProgressBar) findViewById(R.id.loading);
        final SafeSettingViewModel safeSettingViewModel= ViewModelProviders.of(this).get(SafeSettingViewModel.class);
        try {
            IOUtil.writeFileDataTobytes(FILENAME, curUserBean, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        registerViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double newMoney = Double.parseDouble(charge_moneny.getText().toString());
                String Password = password.getText().toString();
                curUserBean.setMoney(newMoney + curUserBean.getMoney());
                curUserBean.setPassword(Password);
                try {
                    loading.setVisibility(View.VISIBLE);
//                    safeSettingViewModel.setUserBean(Password,Telephone,Address,Name);
//                    safeSettingViewModel.update();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        final Observer<Integer> flagObserver = new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer flag) {
                if (flag != 0) {
                    AccountPageViewModel.setUserBean(curUserBean);
                    try {
                        IOUtil.writeFileDataTobytes(FILENAME, safeSettingViewModel.getUserBean().getValue(), SetmoneyActivity.this);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    finish();
                    overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
                } else {
                    safeSettingViewModel.setUserBean(AccountPageViewModel.getUserBean());
                    loading.setVisibility(View.GONE);
                    System.out.println("error");
                }
            }
        };
        safeSettingViewModel.getFlag().observe(this, flagObserver);
    }
}
