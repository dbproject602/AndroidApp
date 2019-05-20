package com.example.db_project.data;

import java.util.ArrayList;

public class Orderlist {

    private static boolean is_cache = false;
    private static boolean is_updated = false;

    private static ArrayList<Orderinfo> Orderlist = new ArrayList<>();

    public Orderinfo createOrder(String shop, String status, String date, ArrayList<Foodinfo> items){
        Orderinfo order = new Orderinfo(shop,status,date,items);
        return order;
    }

    public static boolean isIs_cache() {
        return is_cache;
    }

    public static void setIs_cache(boolean is_cache) {
        com.example.db_project.data.Orderlist.is_cache = is_cache;
    }

    public static boolean isIs_updated() {
        return is_updated;
    }

    public static void setIs_updated(boolean is_updated) {
        com.example.db_project.data.Orderlist.is_updated = is_updated;
    }

    public static ArrayList<Orderinfo> getOrderlist() {
        return Orderlist;
    }

    public static void setOrderlist(ArrayList<Orderinfo> orderlist) {
        Orderlist = orderlist;
    }

}

