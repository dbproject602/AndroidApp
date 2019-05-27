package service;

import android.os.Handler;

import bean.OrderBean;
import dao.OrderDao;
import dao.OrderDaoImpl;

public class OrderServiceImpl implements OrderService {
    OrderDao orderDao =new OrderDaoImpl();

    @Override
    public void fetchOrderList(int userId, Handler handler) throws Exception {
        orderDao.fetchOrderList(userId,handler);
    }

    @Override
    public int deleteOrderById(int orderId, Handler handler) throws Exception {
        orderDao.deleteOrderById(orderId,handler);
        return 0;
    }

    @Override
    public int updateOrder(OrderBean orderBean, Handler handler) throws Exception {
        orderDao.updateOrder(orderBean,handler);
        return 0;
    }

    @Override
    public int addOrder(OrderBean orderBean, Handler handler) throws Exception {
        orderDao.addOrder(orderBean,handler);
        return 0;
    }
}
