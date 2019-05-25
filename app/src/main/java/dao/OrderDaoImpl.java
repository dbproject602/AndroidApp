package dao;

import java.util.List;

import bean.OrderBean;

public class OrderDaoImpl implements OrderDao {

    @Override
    public List<OrderBean> fetchOrderList(int userid) throws Exception {
        return null;
    }

    @Override
    public int deleteOrderById(int orderId) throws Exception {
        return 0;
    }

    @Override
    public int updateOrder(OrderBean orderBean) throws Exception {
        return 0;
    }

    @Override
    public int addOrder(OrderBean orderBean) throws Exception {
        return 0;
    }
}
