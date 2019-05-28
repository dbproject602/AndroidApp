package bean;

import java.io.Serializable;

public class FoodBean implements Serializable {
    private String foodId;
    private String foodName;
    private String shopId;
    private double price;
    private int remaining;
    // 食物不需要评级
    public FoodBean(String foodId, String foodName, String shopId, double price, int remaining) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.shopId = shopId;
        this.price = price;
        this.remaining = remaining;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }


}
