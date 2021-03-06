package com.db.viewmodel;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;

import java.util.List;

import bean.ShopBean;
import service.ShopService;
import service.ShopServiceImpl;

public class ShopViewModel extends ViewModel {
    private MutableLiveData<List<ShopBean>> shopBeanList = new MutableLiveData<>();
    private static int shoptype = 0;



    private static String shopname = "";
    @SuppressLint("HandlerLeak")
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            shopBeanList.postValue((List<ShopBean>) msg.obj);
        }
    };
    public void ShowShopList() throws Exception{
        Log.d("debug:Shop",String.valueOf(shoptype));
        ShopService shopService = new ShopServiceImpl();
        shopService.showShopList(shoptype,handler);
    }
    public void ShowShopListbyShopname() throws Exception{
        ShopService shopService = new ShopServiceImpl();
        shopService.showShopListbyName(shopname,handler);
    }
    public static int getShoptype() {
        return shoptype;
    }

    public static void setShoptype(int shop) {
        shoptype = shop;
    }

    public LiveData<List<ShopBean>> getShopBeanList() {
        return shopBeanList;
    }

    public static String getShopname() {
        return shopname;
    }

    public static void setShopname(String shopname) {
        ShopViewModel.shopname = shopname;
    }
}
