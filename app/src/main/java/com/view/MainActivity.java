package com.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapView;
import com.example.activity.R;
import com.adapter.SectionsPagerAdapter;

import java.util.ArrayList;

import bean.FoodBean;

public class MainActivity extends NavigationActivity {

    @SuppressLint("HandlerLeak")
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ArrayList<FoodBean> temp = (ArrayList<FoodBean>)msg.obj;
            System.out.println(temp.get(0).getName());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init_home();
    }

    @Override
    void init_home(){
        setcurrent_Activity(0,this);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    @Override
    void init_arrount(){
        setcurrent_Activity(1,cur_activity);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_map);
        MapView mv = (MapView) findViewById(R.id.mv);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setSelectedItemId(navView.getMenu().getItem(1).getItemId());
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    void init_order(){
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

    @Override
    void init_account(){
        setcurrent_Activity(3,cur_activity);
        setContentView(R.layout.activity_account);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setSelectedItemId(navView.getMenu().getItem(3).getItemId());
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
