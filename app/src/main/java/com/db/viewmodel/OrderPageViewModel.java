package com.db.viewmodel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

import bean.OrderBean;
import bean.ShopBean;
import service.OrderService;
import service.OrderServiceImpl;

public class OrderPageViewModel extends ViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private LiveData<List<OrderBean>> orderList = Transformations.map(mIndex, new Function<Integer, List<OrderBean>>() {
        @Override
        public List<OrderBean> apply(Integer input) {
            List<OrderBean> total =  orderList.getValue();
            if (total == null){
                return null;
            }
            List<OrderBean> result = new ArrayList<OrderBean>();
            switch (input){
                case 1: // 已经下单
                    for(OrderBean orderBean:total){
                       if(orderBean.getState()==0){
                           result.add(orderBean);
                       }
                    }
                    return result;
                case 2:// 全部订单
                    return total;
                case 3://历史订单
                    for(OrderBean orderBean:total){
                        if(orderBean.getState()==2){
                            result.add(orderBean);
                        }
                    }
                    return result;
                default:
                    return null;
            }

        }
    });
    public void fetchOrderList(int userid){
        OrderService orderService = new OrderServiceImpl();

    }
    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<List<OrderBean>> getOrderList() {
        return orderList;
    }
}