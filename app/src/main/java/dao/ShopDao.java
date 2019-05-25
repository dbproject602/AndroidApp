package dao;

import android.os.Handler;

import java.util.List;

import bean.ShopBean;

public interface ShopDao {
    List<ShopBean> fetchShopList(int shopType) throws Exception;
}
