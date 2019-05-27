package service;

import android.os.Handler;

import bean.OrderBean;

public interface OrderService {
    void fetchOrderList(int userId, Handler handler) throws Exception;
    int deleteOrderById(int orderId,Handler handler) throws Exception;
    int updateOrder(OrderBean orderBean, Handler handler) throws Exception;
    int addOrder(OrderBean orderBean,Handler handler) throws  Exception;

}
