package dao;

import android.os.Handler;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import util.HttpManager;

public class ShopDaoImpl implements ShopDao {
    public void searchShopbyShopname(String shopname, Handler handler) throws Exception{
        String servlet = "searchShopbyShopnameServlet";
        RequestBody requestBody = new FormBody.Builder().add("shopname",String.valueOf(shopname)).build();
        HttpManager.send(requestBody,servlet,handler);
    }
    public void searchShopbyType(String type, Handler handler) throws Exception{
        String servlet = "searchShopbyShopnameServlet";
        RequestBody requestBody = new FormBody.Builder().add("type",String.valueOf(type)).build();
        HttpManager.send(requestBody,servlet,handler);
    }
}
