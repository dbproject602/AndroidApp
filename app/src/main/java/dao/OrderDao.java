package dao;

import bean.FoodBean;
import bean.OrderBean;

import java.util.ArrayList;
import java.util.List;

public interface OrderDao {
    List<OrderBean> fetchOrderList(int userid) throws Exception;
    int deleteOrderById(int orderId) throws Exception;
    int updateOrder(OrderBean orderBean) throws Exception;
    int addOrder(OrderBean orderBean) throws  Exception;
}

