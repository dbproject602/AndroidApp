package bean;

import bean.FoodBean;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class OrderBean implements Serializable {
    private int orderId;
    private int userId;
    private String shopId;
    private int senderId;
    private Date startTime;
    private Date endTime;
    private List<FoodBean> foodItems;
    private ShopBean shopBean;
    private SenderBean senderBean;
    private int state; //需要记录Order的状态，三个状态0已下单，1派送中，2结单
    public OrderBean( int userId, String shopId, List<FoodBean> items) {
        this.orderId = 0;
        this.userId = userId;
        this.shopId = shopId;
        this.senderId = 0;
        this.startTime = null;
        this.endTime = null;
        this.foodItems = items;
        this.state = 0;
    }
    public OrderBean(int orderId, int userId, String shopId, int senderId, Date startTime, Date endTime, List<FoodBean> items,int state, ShopBean shopBean, SenderBean senderBean) {
        this.orderId = orderId;
        this.userId = userId;
        this.shopId = shopId;
        this.senderId = senderId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.foodItems = items;
        this.state = state;
        this.shopBean = shopBean;
        this.senderBean = senderBean;
    }
    public int getState() { return state; }

    public void setState(int state) {
        this.state = state;
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

    public void setFoodItems(List<FoodBean> foodItems) {
        this.foodItems = foodItems;
    }

    public ShopBean getShopBean(){return shopBean;}

    public void setShopBean(ShopBean shopBean){this.shopBean = shopBean;}

    public SenderBean getSenderBean(){return senderBean;}

    public void setSenderBean(SenderBean senderBean){this.senderBean = senderBean;}
}