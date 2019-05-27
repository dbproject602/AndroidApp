package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShopBean implements Serializable {
    private int shopId;
    private String shopName;
    private String password;
    private int shopType;
    private String telephone;
    private String address;
    private boolean isOpen;
    private double reputation;
    private double sendRange;
    private double longitude;
    private double latitude;
    private List<FoodBean> foodItems;
    private List<SenderBean> senderItems;

    public ShopBean(int shopId, String shopName, String password, int shopType,
                    String telephone, String address, boolean isOpen,
                    double reputation, double sendRange, double longitude, double latitude,
                    List<FoodBean> foodItems, List<SenderBean> senderItems) {
        this.shopId = shopId;
        this.shopName = shopName;
        this.password = password;
        this.shopType = shopType;
        this.telephone = telephone;
        this.address = address;
        this.isOpen = isOpen;
        this.reputation = reputation;
        this.sendRange = sendRange;
        this.longitude = longitude;
        this.latitude = latitude;
        this.foodItems = foodItems;
        this.senderItems = senderItems;
    }

    public List<FoodBean> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(List<FoodBean> foodItems) {
        this.foodItems = foodItems;
    }

    public List<SenderBean> getSenderItems() {
        return senderItems;
    }

    public void setSenderItems(List<SenderBean> senderItems) {
        this.senderItems = senderItems;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getShopType() {
        return shopType;
    }

    public void setShopType(int shopType) {
        this.shopType = shopType;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public double getReputation() {
        return reputation;
    }

    public void setReputation(double reputation) {
        this.reputation = reputation;
    }

    public double getSendRange() {
        return sendRange;
    }

    public void setSendRange(double sendRange) {
        this.sendRange = sendRange;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }


}

