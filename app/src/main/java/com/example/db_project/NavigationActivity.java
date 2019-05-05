package com.example.db_project;

import android.app.Activity;
import android.os.Debug;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.content.Intent;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapView;
import com.example.db_project.ui.main.SectionsPagerAdapter;

public abstract class NavigationActivity extends AppCompatActivity {

    private static int current_Activity = 0;
    private static Activity cur_activity;
    void setcurrent_Activity(int activity,Activity act){
        current_Activity = activity;
        cur_activity = act;
    }

    private void init_home(){
        setcurrent_Activity(0,cur_activity);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void init_arrount(){
        setcurrent_Activity(1,cur_activity);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_map);
        MapView mv = (MapView) findViewById(R.id.mv);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setSelectedItemId(navView.getMenu().getItem(1).getItemId());
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void init_order(){
        setcurrent_Activity(2,cur_activity);
        setContentView(R.layout.activity_order);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(cur_activity, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setSelectedItemId(navView.getMenu().getItem(2).getItemId());
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void init_account(){
        setcurrent_Activity(3,cur_activity);
        setContentView(R.layout.activity_account);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setSelectedItemId(navView.getMenu().getItem(3).getItemId());
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if(current_Activity==0){
                        return true;
                    }else {
                        init_home();
                        return true;
                    }
                case R.id.navigation_around:
                    if(current_Activity==1){
                        return true;
                    }else {
                        init_arrount();
                        return true;
                    }
                case R.id.navigation_order:
                    if(current_Activity==2){
                        return true;
                    }else {
                        init_order();
                        return  true;
                    }
                case R.id.navigation_account:
                    if(current_Activity==3){
                        return true;
                    }else {
                        init_account();
                        return true;
                    }
            }
            return false;
        }
    };
}
