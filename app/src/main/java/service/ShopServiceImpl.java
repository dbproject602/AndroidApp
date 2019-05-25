package service;

import dao.ShopDao;
import dao.ShopDaoImpl;

public class ShopServiceImpl implements ShopService {
    ShopDao shopDao = new ShopDaoImpl();
}
