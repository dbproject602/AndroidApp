package com.db.viewmodel;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;
import android.os.Message;

import bean.ShopBean;
import service.ShopService;
import service.ShopServiceImpl;

public class ShopViewModel extends ViewModel {
    private MutableLiveData<ShopBean> shopBean = new MutableLiveData<>();
    private int shoptype = 0;
    @SuppressLint("HandlerLeak")
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            shopBean.postValue((ShopBean) msg.obj);
        }
    };
    public void ShowShopList(int  shoptype) throws Exception{
        ShopService shopService = new ShopServiceImpl();
        shopService.showShopList(shoptype,handler);

    }

    public LiveData<ShopBean> getUserBean() {
        return shopBean;
    }
}
