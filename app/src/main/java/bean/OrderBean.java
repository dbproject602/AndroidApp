package bean;

import bean.FoodBean;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OrderBean implements Serializable {
    private int orderId;
    private int userId;
    private String shopId;
    private int senderId;
    private Date startTime;
    private Date endTime;
    private List<FoodBean> foodItems;
    private String status; //需要记录Order的状态，三个状态已下单，派送中，结单

    public OrderBean( int userId, String shopId, List<FoodBean> items) {
        this.orderId = 0;
        this.userId = userId;
        this.shopId = shopId;
        this.senderId = 0;
        this.startTime = null;
        this.endTime = null;
        this.foodItems = items;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<FoodBean> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(ArrayList<FoodBean> foodItems) {
        this.foodItems = foodItems;
    }
}