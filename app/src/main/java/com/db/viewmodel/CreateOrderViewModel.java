package com.db.viewmodel;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;
import android.os.Message;

import java.util.List;

import bean.FoodBean;
import bean.OrderBean;
import bean.ShopBean;
import bean.UserBean;
import service.OrderService;
import service.OrderServiceImpl;

public class CreateOrderViewModel extends ViewModel {
    private static List<FoodBean> foodBeanList = null;
    private static UserBean userBean = null;
    private static ShopBean shopBean = null;
    public static List<FoodBean> getFoodBeanList() {
        return foodBeanList;
    }
    @SuppressLint("HandlerLeak")
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };
    public static void setFoodBeanList(List<FoodBean> foodBeanList) {
        CreateOrderViewModel.foodBeanList = foodBeanList;
    }

    public static UserBean getUserBean() {
        return userBean;
    }

    public static void setUserBean(UserBean userBean) {
        CreateOrderViewModel.userBean = userBean;
    }

    public static ShopBean getShopBean() {
        return shopBean;
    }

    public static void setShopBean(ShopBean shopBean) {
        CreateOrderViewModel.shopBean = shopBean;
    }
    public void submit() throws Exception {
        OrderBean orderBean = new OrderBean(userBean.getUserId(),shopBean.getShopId(),foodBeanList);
        OrderService orderService = new OrderServiceImpl();
        orderService.addOrder(orderBean,handler);
    }
}
