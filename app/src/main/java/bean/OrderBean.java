package bean;

import bean.FoodBean;

import java.io.Serializable;
import java.util.ArrayList;

public class OrderBean implements Serializable {
    private String shop;
    private String status;
    private String date;
    private ArrayList<FoodBean> items;

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<FoodBean> getItems() {
        return items;
    }

    public void setItems(ArrayList<FoodBean> items) {
        this.items = items;
    }

    public OrderBean(String shop, String status, String date, ArrayList<FoodBean> items) {
        this.shop = shop;
        this.status = status;
        this.date = date;
        this.items = (ArrayList<FoodBean>) items.clone();
    }
}