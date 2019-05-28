package com.db.viewmodel;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;
import java.util.List;

import bean.FoodBean;
import bean.ShopBean;
import service.FoodService;
import service.FoodServiceImpl;

public class FoodViewModel extends ViewModel {
    private MutableLiveData<List<FoodBean>> foodBeanList = new MutableLiveData<>();
    private List<FoodBean> chooseList = new ArrayList<>();
    private static ShopBean shopBean;
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
        foodService.ShowFoodList(shopBean.getShopId(),handler);

    }
    public void submit() throws Exception{
            CreateOrderViewModel.setFoodBeanList(chooseList);
    }
    public void addChoose(FoodBean foodBean){
        chooseList.add(foodBean);
    }
    public static ShopBean getshopBean() {
        return shopBean;
    }

    public static void setShopBean(ShopBean shop) {
        shopBean = shop;
    }

    public LiveData<List<FoodBean>> getShopBeanList() {
        return foodBeanList;
    }
}
