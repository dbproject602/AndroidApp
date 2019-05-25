package com.db.view;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Button;
import android.widget.SearchView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.Poi;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapView;
import com.db.viewmodel.AccountPageViewModel;
import com.db.viewmodel.HomePageViewModel;
import com.db.viewmodel.LoginViewModel;
import com.example.activity.R;
import com.db.adapter.SectionsPagerAdapter;

import java.util.ArrayList;

import bean.FoodBean;
import util.LocationManager;

public class MainActivity extends NavigationActivity {
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationManager = new LocationManager(this);
        locationManager.registerListener(mListener);
        locationManager.setLocationOption(locationManager.getDefaultLocationClientOption());
        init_home();
        locationManager.start();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationManager.unregisterListener(mListener); //注销掉监听
        locationManager.stop(); //停止定位服务
    }


    @Override
    void init_home(){
        setcurrent_Activity(0,this);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Button china_btn = findViewById(R.id.r_chinese);
        Button west_btn = findViewById(R.id.r_west);
        Button fast_btn = findViewById(R.id.r_fast);
        Button janpan_btn = findViewById(R.id.r_chinese);
        SearchView searchView = findViewById(R.id.searchView);
        final HomePageViewModel model = ViewModelProviders.of(this).get(HomePageViewModel.class);

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
        final AccountPageViewModel model = ViewModelProviders.of(this).get(AccountPageViewModel.class);

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
        final AccountPageViewModel model = ViewModelProviders.of(this).get(AccountPageViewModel.class);

    }

    private BDAbstractLocationListener mListener = new BDAbstractLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // TODO Auto-generated method stub
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                StringBuilder sb = new StringBuilder(256);
                sb.append("\nlatitude : ");// 纬度
                sb.append(location.getLatitude());
                sb.append("\nlontitude : ");// 经度
                sb.append(location.getLongitude());
                sb.append("\naddr : ");// 地址信息
                sb.append(location.getAddrStr());

                System.out.println(sb);
            }
        }

    };


}
