package com.db.viewmodel;

import android.annotation.SuppressLint;
import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;
import java.util.List;

import bean.OrderBean;
import bean.ShopBean;
import service.OrderService;
import service.OrderServiceImpl;

public class OrderPageViewModel extends ViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();

    private static MutableLiveData<List<OrderBean>> total = new MutableLiveData<>();

    private LiveData<List<OrderBean>> orderList = Transformations.map(mIndex, new Function<Integer, List<OrderBean>>() {
        @Override
        public List<OrderBean> apply(Integer input) {
            if (total.getValue() == null){
                return new ArrayList<OrderBean>();
            }
            List<OrderBean> result = new ArrayList<OrderBean>();

            switch (input){
                case 1: // 已经下单
                    for(OrderBean orderBean:total.getValue()){
                       if(orderBean.getState()==0){
                           result.add(orderBean);
                       }
                    }
                    return result;
                case 2:// 全部订单
                    return total.getValue();
                case 3://历史订单
                    for(OrderBean orderBean:total.getValue()){
                        if(orderBean.getState()==2){
                            result.add(orderBean);
                        }
                    }
                    System.out.println("size is "+result.size());
                    return result;
                default:
                    return null;
            }

        }
    });
    @SuppressLint("HandlerLeak")
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            total.setValue((List<OrderBean>) msg.obj);  //  怎么处理
        }
    };
    public void fetchOrderList(int userid){
        OrderService orderService = new OrderServiceImpl();
        try {
            orderService.fetchOrderList(userid,handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<List<OrderBean>> getOrderList() {
        return orderList;
    }

    public static LiveData<List<OrderBean>> getTotal() {
        return total;
    }

}