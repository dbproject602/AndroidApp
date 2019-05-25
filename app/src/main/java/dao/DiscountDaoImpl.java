package dao;

import android.os.Handler;

import java.util.List;

import bean.DiscountBean;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import util.HttpManager;

public class DiscountDaoImpl implements DiscountDao {
    @Override
    public void fetchDiscountList(int shopId, Handler handler) throws Exception {
        String servlet = "FetchDiscountListServlet";
        RequestBody requestBody = new FormBody.Builder().add("shopid",String.valueOf(shopId)).build();
        HttpManager.send(requestBody,servlet,handler);
    }
}
