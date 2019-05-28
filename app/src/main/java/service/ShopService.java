package service;

import android.os.Handler;

public interface ShopService {
    void showShopList(int shoptype, Handler handler) throws Exception;
    void showShopListbyName(String shopname,Handler handler) throws Exception;
}
