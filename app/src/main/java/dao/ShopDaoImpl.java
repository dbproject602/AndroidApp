package dao;

import android.os.Handler;

import java.util.List;

import bean.ShopBean;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import util.HttpManager;

public class ShopDaoImpl implements ShopDao {

    @Override
    public void fetchShopList(int shopType,Handler handler) throws Exception {
        String servlet = "FetchShopListByTypeServlet";
        RequestBody requestBody = new FormBody.Builder().add("shoptype",String.valueOf(shopType)).build();
        HttpManager.send(requestBody,servlet,handler);
    }
}
