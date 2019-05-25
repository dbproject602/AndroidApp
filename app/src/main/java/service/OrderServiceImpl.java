package service;

import dao.OrderDao;
import dao.OrderDaoImpl;

public class OrderServiceImpl implements OrderService {
    OrderDao orderDao =new OrderDaoImpl();

}
