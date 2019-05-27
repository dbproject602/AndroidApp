package com.db.viewmodel;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;
import android.os.Message;

import bean.FoodBean;
import service.FoodService;
import service.FoodServiceImpl;

public class FoodViewModel extends ViewModel {
    private MutableLiveData<FoodBean> foodBean = new MutableLiveData<>();
    private String shopid = "";
    @SuppressLint("HandlerLeak")
    Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            foodBean.postValue((FoodBean) msg.obj);
        }
    };
    public void ShowFoodList(int  shopid) throws Exception{
        FoodService foodService = new FoodServiceImpl();
        foodService.ShowFoodList(shopid,handler);

    }

    public LiveData<FoodBean> getUserBean() {
        return foodBean;
    }
}
