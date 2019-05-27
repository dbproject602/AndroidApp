package com.db.viewmodel;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;
import android.os.Message;

import java.util.List;

import bean.FoodBean;
import service.FoodService;
import service.FoodServiceImpl;

public class FoodViewModel extends ViewModel {
    private MutableLiveData<List<FoodBean>> foodBeanList = new MutableLiveData<>();

    private String shopid = "";
    @SuppressLint("HandlerLeak")
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            foodBeanList.postValue((List<FoodBean>) msg.obj);
        }
    };
    public void ShowFoodList() throws Exception{
        FoodService foodService = new FoodServiceImpl();
        foodService.ShowFoodList(shopid,handler);

    }
    public String getshopid() {
        return shopid;
    }

    public void setshopid(String shopid) {
        this.shopid = shopid;
    }

    public LiveData<List<FoodBean>> getShopBeanList() {
        return foodBeanList;
    }
}
