package service;

import android.os.Handler;

import java.util.List;

import bean.FoodBean;

public interface FoodService {
    void fetchFoodList(int shopid, Handler handler) throws Exception;

}
