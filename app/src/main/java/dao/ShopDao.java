package dao;

import android.os.Handler;

public interface ShopDao {
    void fetchShopList(int shopType,Handler handler) throws Exception;
}
