package service;

import android.os.Handler;

import dao.ShopDao;
import dao.ShopDaoImpl;

public class ShopServiceImpl implements ShopService {
    ShopDao shopDao = new ShopDaoImpl();
    @Override
    public void showShopList(int shoptype, Handler handler) throws Exception{
        shopDao.fetchShopList(shoptype,handler);
    }

    @Override
    public void showShopListbyName(String shopName, Handler handler) throws Exception {
        shopDao.fetchShopListbyName(shopName,handler);
    }

    @Override
    public void showShopListbyDis(double longitude, double latitude, Handler handler) throws Exception {
        shopDao.fetchShopListbyDis(longitude,latitude,handler);
    }
}
