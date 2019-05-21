package service;

import java.util.List;
import android.os.Handler;
import bean.FoodBean;
import dao.FoodDao;
import dao.FoodDaoImpl;

public class FoodServiceImpl implements FoodService {
    FoodDao foodDao =new FoodDaoImpl();
    @Override
    public void fetchFoodList(int shopid, Handler handler) throws Exception {
        List<FoodBean> foodList=null;
        foodDao.fetchFoodList(shopid,handler);
    }
}
