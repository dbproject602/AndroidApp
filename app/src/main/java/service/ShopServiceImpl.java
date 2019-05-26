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
}
