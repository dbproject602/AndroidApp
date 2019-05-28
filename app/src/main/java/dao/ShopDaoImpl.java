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

    @Override
    public void fetchShopListbyName(String shopName, Handler handler) throws Exception {
      //  String servlet = "FetchShopListServlet";
   //     RequestBody requestBody = new FormBody.Builder().add("shoptype",String.valueOf(shopType)).build();
     //   HttpManager.send(requestBody,servlet,handler);
    }

    @Override
    public void fetchShopListbyDis(double longitude, double latitude,Handler handler) {
        String servlet = "FetchShopListByDisServlet";
        RequestBody requestBody = new FormBody.Builder().add("longitude",String.valueOf(longitude)).add("latitude",String.valueOf(latitude)).build();
        HttpManager.send(requestBody,servlet,handler);
    }

}
