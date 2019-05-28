package service;

import android.os.Handler;

import java.util.List;

import bean.FoodBean;

public interface FoodService {
    void ShowFoodList(String shopid, Handler handler) throws Exception;

}
