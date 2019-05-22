package dao;

import android.os.Handler;

public interface ShopDao {
    void searchShopbyShopname(String shopname, Handler handler) throws Exception;
    void searchShopbyType(String type, Handler handler) throws Exception;


}
