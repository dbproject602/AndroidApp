package com.db.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.activity.R;

import bean.UserBean;


public class LauncherActivity extends AppCompatActivity {

    private ImageView splashImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        //加载启动图片
        setContentView(R.layout.activity_launcher);
        splashImage = findViewById(R.id.splash_image_view);
        final Intent afterSplash = new Intent(LauncherActivity.this, LoginActivity.class);
        Thread timer = new Thread(){
            @Override
            public void run(){
                try{
                    sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    startActivity(afterSplash);
                    finish();
                }
            }
        };
        timer.start();
    }




}
