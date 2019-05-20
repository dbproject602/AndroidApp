package com.example.db_project.data;

import java.util.ArrayList;

class Orderinfo{
    private String shop;
    private String status;
    private String date;
    private ArrayList<Foodinfo> items;

    public Orderinfo(String shop, String status, String date, ArrayList<Foodinfo> items) {
        this.shop = shop;
        this.status = status;
        this.date = date;
        this.items = (ArrayList<Foodinfo>) items.clone();
    }
}