package com.db.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.example.activity.R;

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

    }
}
