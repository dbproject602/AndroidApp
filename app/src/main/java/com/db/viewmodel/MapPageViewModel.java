package com.db.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import bean.ShopBean;
import bean.UserBean;
import service.ShopService;
import service.ShopServiceImpl;

public class MapPageViewModel extends ViewModel {
    private MutableLiveData<double[]> location = new MutableLiveData<>();
    private MutableLiveData<List<ShopBean>> shopBeanList = new MutableLiveData<>();
    public void ShowShopList() throws Exception{
        ShopService shopService = new ShopServiceImpl();
      //  shopService.showShopList(shoptype,handler);
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
