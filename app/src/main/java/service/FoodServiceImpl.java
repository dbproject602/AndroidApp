package service;

import android.os.Handler;
import dao.FoodDao;
import dao.FoodDaoImpl;

public class FoodServiceImpl implements FoodService {
    FoodDao foodDao =new FoodDaoImpl();
    @Override
    public void ShowFoodList(String shopid, Handler handler) throws Exception {
        foodDao.fetchFoodList(shopid,handler);
    }
}
