package dao;

import bean.FoodBean;
import bean.OrderBean;

import java.util.ArrayList;
import java.util.List;
import android.os.Handler;

public interface OrderDao {
    void fetchOrderList(int userId, Handler handler) throws Exception;
    int deleteOrderById(int orderId,Handler handler) throws Exception;
    int updateOrder(OrderBean orderBean,Handler handler) throws Exception;
    int addOrder(OrderBean orderBean,Handler handler) throws  Exception;
}

