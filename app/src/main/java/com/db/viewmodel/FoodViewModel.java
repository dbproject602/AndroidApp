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

    private int shopid = 0;
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
    public int getshopid() {
        return shopid;
    }

    public void setshopid(int shopid) {
        this.shopid = shopid;
    }

    public LiveData<List<FoodBean>> getShopBeanList() {
        return foodBeanList;
    }
}
