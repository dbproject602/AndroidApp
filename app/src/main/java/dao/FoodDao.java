package dao;

import java.util.List;
import android.os.Handler;

import bean.FoodBean;

public interface FoodDao {
    void fetchFoodList(int shopid, Handler handler) throws Exception;
}
