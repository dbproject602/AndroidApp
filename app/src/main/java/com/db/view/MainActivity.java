package com.db.view;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.ViewSwitcher;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.Poi;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.db.viewmodel.AccountPageViewModel;
import com.db.viewmodel.HomePageViewModel;
import com.db.viewmodel.LoginViewModel;
import com.db.viewmodel.MapPageViewModel;
import com.db.viewmodel.ShopViewModel;
import com.example.activity.R;
import com.db.adapter.SectionsPagerAdapter;

import java.util.ArrayList;

import bean.FoodBean;
import bean.UserBean;
import util.LocationManager;

public class MainActivity extends NavigationActivity {
    private LocationManager locationManager;
    private Context context;
    private MapPageViewModel mapPageViewModel;
    private BaiduMap baiduMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        init_home();
        context = MainActivity.this;

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationManager.unregisterListener(mListener); //注销掉监听
        locationManager.stop(); //停止定位服务
    }


    @Override
    void init_home(){
        setpage(0);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Button china_btn = findViewById(R.id.r_chinese);
        Button west_btn = findViewById(R.id.r_west);
        Button fast_btn = findViewById(R.id.r_fast);
        Button janpan_btn = findViewById(R.id.r_japanese);
        SearchView searchView = findViewById(R.id.searchView);
        final HomePageViewModel model = ViewModelProviders.of(this).get(HomePageViewModel.class);
        final ShopViewModel shopmodel = ViewModelProviders.of(this).get(ShopViewModel.class);
        china_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shopmodel.setShoptype(0);
                Intent intent = new Intent(MainActivity.this,ShopActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out,
                        R.anim.slide_in);
                finish();
            }
        });
        west_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shopmodel.setShoptype(1);
                Intent intent = new Intent(MainActivity.this,ShopActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out,
                        R.anim.slide_in);
                finish();
            }
        });
        fast_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shopmodel.setShoptype(2);
                Intent intent = new Intent(MainActivity.this,ShopActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out,
                        R.anim.slide_in);
                finish();
            }
        });
        janpan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shopmodel.setShoptype(3);
                Intent intent = new Intent(MainActivity.this,ShopActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out,
                        R.anim.slide_in);
                finish();
            }
        });
    }


    @Override
    void init_arround(){
        setpage(1);
        setContentView(R.layout.activity_map);
        mapPageViewModel = ViewModelProviders.of(this).get(MapPageViewModel.class);
        locationManager = new LocationManager(this);
        locationManager.registerListener(mListener);
        locationManager.setLocationOption(locationManager.getDefaultLocationClientOption());
        locationManager.start();
        MapView mv = (MapView) findViewById(R.id.mv);
        baiduMap = mv.getMap();
     //   BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.ic_chevron_left_black_40dp);
    //    OverlayOptions options = new MarkerOptions().icon(icon).position(point);
     //   baiduMap.addOverlay(options);
        //定义地图状态

        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        final Observer<double[]> locationObserver = new Observer<double[]>() {
            @Override
            public void onChanged(@Nullable double[] location) {
                LatLng point = new LatLng(location[0],location[1]);
                MapStatus mMapStatus = new MapStatus.Builder()
                        .target(point)
                        .zoom(12)
                        .build();
                MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
                //改变地图状态
                baiduMap.setMapStatus(mMapStatusUpdate);
            }
        };
        mapPageViewModel.getLocation().observe(this,locationObserver);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setSelectedItemId(navView.getMenu().getItem(1).getItemId());
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        final AccountPageViewModel model = ViewModelProviders.of(this).get(AccountPageViewModel.class);

    }

    @Override
    void init_order(){
        setpage(2);
        setContentView(R.layout.activity_order);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(MainActivity.this, getSupportFragmentManager());
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
        setpage(3);
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
                mapPageViewModel.setLocation(location.getLatitude(),location.getLongitude());
                locationManager.unregisterListener(mListener); //注销掉监听
                locationManager.stop(); //停止定位服务
            }
        }

    };


}
