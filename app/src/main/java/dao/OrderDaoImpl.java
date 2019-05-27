package dao;

import java.util.List;

import android.os.Handler;

import bean.OrderBean;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import util.HttpManager;

public class OrderDaoImpl implements OrderDao {

    @Override
    public void fetchOrderList(int userId, Handler handler) throws Exception {
        String servlet = "FetchOrderListServlet";
        RequestBody requestBody = new FormBody.Builder().add("userid",String.valueOf(userId)).build();
        HttpManager.send(requestBody,servlet,handler);
    }

    @Override
    public int deleteOrderById(int orderId,Handler handler) throws Exception {
        return 0;
    }

    @Override
    public int updateOrder(OrderBean orderBean,Handler handler) throws Exception {
        return 0;
    }

    @Override
    public int addOrder(OrderBean orderBean,Handler handler) throws Exception {
        String servlet = "AddOrderServlet";
        RequestBody requestBody = new FormBody.Builder().add("orderBean",String.valueOf(orderBean)).build();
        HttpManager.update(requestBody,servlet,handler);
        return 0;
    }
}
