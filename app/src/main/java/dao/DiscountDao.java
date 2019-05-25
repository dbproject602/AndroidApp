package dao;

import android.os.Handler;

import java.util.List;

import bean.DiscountBean;

public interface DiscountDao {
    void fetchDiscountList(int shopId, Handler handler) throws Exception;
}
