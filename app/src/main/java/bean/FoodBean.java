package bean;

import java.io.Serializable;

public class FoodBean implements Serializable {
    private int foodId;
    private String foodName;
    private int shopId;
    private double price;
    private int remaining;
    // 食物不需要评级
    public FoodBean(int foodId, String foodName, int shopId, double price, int remaining) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.shopId = shopId;
        this.price = price;
        this.remaining = remaining;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
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
