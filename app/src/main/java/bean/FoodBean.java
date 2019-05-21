package bean;

import java.io.Serializable;

public class FoodBean implements Serializable {
    private String foodname;
    private int price;

    public String getName() {
        return foodname;
    }

    public void setName(String name) {
        this.foodname = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public FoodBean(String name, int price) {
        this.foodname = name;
        this.price = price;
    }
}
