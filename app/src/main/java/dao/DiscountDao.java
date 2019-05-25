package dao;

import java.util.List;

import bean.DiscountBean;

public interface DiscountDao {
    List<DiscountBean> fetchShopList(int shopId) throws Exception;
}
