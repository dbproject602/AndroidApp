package com.db.view;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.Poi;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
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
import java.util.List;

import bean.FoodBean;
import bean.ShopBean;
import bean.UserBean;
import util.IOUtil;
import util.LocationManager;

public class MainActivity extends AppCompatActivity {
    private LocationManager locationManager;
    private Context context;
    private MapPageViewModel mapPageViewModel;
    private BaiduMap baiduMap;
    private ImageView accountImg;
    private TextView name;
    private TextView location;
    private TextView phone;
    private Button like;
    private Button pay;
    private Button push;
    private Button information;
    private Button logout;
    private BottomNavigationView navView;
    private String FILENAME = "userBean.dat";
    private static int curPgae = 0;
    private LatLng point;
    private void setpage(int pagenum){
        curPgae = pagenum;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init_home();
        context = MainActivity.this;

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    void init_home(){
        setpage(0);
        setContentView(R.layout.activity_main);
        navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Button china_btn = findViewById(R.id.r_chinese);
        Button west_btn = findViewById(R.id.r_west);
        Button fast_btn = findViewById(R.id.r_fast);
        Button janpan_btn = findViewById(R.id.r_japanese);
        final SearchView searchView = findViewById(R.id.searchView);
        final HomePageViewModel model = ViewModelProviders.of(this).get(HomePageViewModel.class);
        try {
            IOUtil.writeFileDataTobytes(FILENAME,AccountPageViewModel.getUserBean(),this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopViewModel.setShopname(searchView.getQuery().toString());
                ShopViewModel.setShoptype(-1);
                Intent intent = new Intent(MainActivity.this,ShopActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out,
                        R.anim.slide_in);

            }
        });
        china_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopViewModel.setShoptype(1);
                Intent intent = new Intent(MainActivity.this,ShopActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out,
                        R.anim.slide_in);
            }
        });
        west_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopViewModel.setShoptype(2);
                Intent intent = new Intent(MainActivity.this,ShopActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out,
                        R.anim.slide_in);
            }
        });
        fast_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopViewModel.setShoptype(3);
                Intent intent = new Intent(MainActivity.this,ShopActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out,
                        R.anim.slide_in);
            }
        });
        janpan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopViewModel.setShoptype(4);
                Intent intent = new Intent(MainActivity.this,ShopActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out,
                        R.anim.slide_in);
            }
        });
    }


    void init_arround(){
        SDKInitializer.initialize(getApplicationContext());
        setpage(1);
        setContentView(R.layout.activity_map);
        mapPageViewModel = ViewModelProviders.of(this).get(MapPageViewModel.class);
        locationManager = new LocationManager(this);
        locationManager.registerListener(mListener);
        locationManager.setLocationOption(locationManager.getDefaultLocationClientOption());
        locationManager.start();
        MapView mv = (MapView) findViewById(R.id.mv);
        baiduMap = mv.getMap();
        baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                ShopBean shopBean = (ShopBean)marker.getExtraInfo().get("shopBean");
                Button location = new Button(getApplicationContext());
                location.setText(shopBean.getShopName());
                LatLng markpo = marker.getPosition();
                InfoWindow mInfoWindow = new InfoWindow(location, markpo,-100);
                baiduMap.showInfoWindow(mInfoWindow);
                System.out.println(shopBean.getShopName());
                return false;
            }
        });
        mv.getParent().requestDisallowInterceptTouchEvent(true);
        final Observer<double[]> locationObserver = new Observer<double[]>() {
            @Override
            public void onChanged(@Nullable double[] location) {
                point = new LatLng(22.598739,114.00596);
               // point = new LatLng(location[0],location[1]);
                MapStatus mMapStatus = new MapStatus.Builder()
                        .target(point)
                        .zoom(16)
                        .build();
                MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
                BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.self);
                OverlayOptions options = new MarkerOptions().icon(icon).position(point);
                Marker marker = (Marker) (baiduMap.addOverlay(options));
                //改变地图状态
                try {
                    mapPageViewModel.showShopListbyDis(point.longitude,point.latitude);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                baiduMap.setMapStatus(mMapStatusUpdate);
            }
        };
        final Observer<List<ShopBean>> shoplistObserver = new Observer<List<ShopBean>>(){
            @Override
            public void onChanged(@Nullable List<ShopBean> shoplist) {
                OverlayOptions options = null;
                for (final ShopBean shopBean:shoplist){
                    final LatLng po = new LatLng(shopBean.getLatitude(),shopBean.getLongitude());
                    BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.loc);
                    options = new MarkerOptions().icon(icon).position(po);
                    Marker marker = (Marker) (baiduMap.addOverlay(options));
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("shopBean",shopBean);
                    marker.setExtraInfo(bundle);
                }
            }
        };



        mapPageViewModel.getLocation().observe(this,locationObserver);
        mapPageViewModel.getShopBeanList().observe(this,shoplistObserver);
        navView = findViewById(R.id.nav_view);
        navView.setSelectedItemId(navView.getMenu().getItem(1).getItemId());
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    void init_order(){
        setpage(2);
        setContentView(R.layout.activity_order);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(MainActivity.this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        navView = findViewById(R.id.nav_view);
        navView.setSelectedItemId(navView.getMenu().getItem(2).getItemId());
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    void init_account(){
        setpage(3);
        setContentView(R.layout.activity_account);
        accountImg = (ImageView) findViewById(R.id.account_img);
        name = (TextView) findViewById(R.id.name);
        location = (TextView) findViewById(R.id.location);
        phone = (TextView) findViewById(R.id.phone);
        like = (Button) findViewById(R.id.like);
        pay = (Button) findViewById(R.id.pay);
        push = (Button) findViewById(R.id.push);
        information = (Button) findViewById(R.id.information);
        logout = (Button) findViewById(R.id.logout);
        name.setText(AccountPageViewModel.getUserBean().getName());
        phone.setText(AccountPageViewModel.getUserBean().getTelephone());
        location.setText(AccountPageViewModel.getUserBean().getAddress());

        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SafeSettingActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out,R.anim.slide_in);
            }
        });
        navView = findViewById(R.id.nav_view);
        navView.setSelectedItemId(navView.getMenu().getItem(3).getItemId());
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    IOUtil.writeFileDataTobytes(FILENAME,null,MainActivity.this);
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
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

    BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    System.out.println("1");
                    if(curPgae==0){
                        return true;
                    }else {
                        init_home();
                        return true;
                    }
                case R.id.navigation_around:
                    System.out.println("2");
                    if(curPgae==1){
                        return true;
                    }else {
                        init_arround();
                        return true;
                    }
                case R.id.navigation_order:
                    System.out.println("3");
                    if(curPgae==2){
                        return true;
                    }else {
                        init_order();
                        return  true;
                    }
                case R.id.navigation_account:
                    System.out.println("4");
                    if(curPgae==3){
                        return true;
                    }else {
                        init_account();
                        return true;
                    }
            }
            return false;
        }
    };

    private Bitmap drawableToBitamp(Drawable drawable)
    {
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        return bitmap;
    }
}
