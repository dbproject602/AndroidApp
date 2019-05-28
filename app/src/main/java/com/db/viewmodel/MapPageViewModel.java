package com.db.viewmodel;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;
import java.util.List;

import bean.ShopBean;
import bean.UserBean;
import service.ShopService;
import service.ShopServiceImpl;

public class MapPageViewModel extends ViewModel {
    private MutableLiveData<double[]> location = new MutableLiveData<>();
    private  MutableLiveData<List<ShopBean>> shopBeanList = new MutableLiveData<>();
    @SuppressLint("HandlerLeak")
    android.os.Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            System.out.println(msg.obj.toString());
            shopBeanList.postValue((List<ShopBean>)msg.obj);
        }
    };

    public void showShopListbyDis(double longt,double lati) throws Exception{
        ShopService shopService = new ShopServiceImpl();
        shopService.showShopListbyDis(longt,lati,handler);
    }

    public void setLocation(double latitude,double longitude) {
        this.location.setValue(new double[]{latitude,longitude});
    }
    public LiveData<double[]> getLocation() {
        return location;
    }

    public LiveData<List<ShopBean>> getShopBeanList() {
        return shopBeanList;
    }


}
