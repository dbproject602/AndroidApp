package com.example.db_project.data;

import java.util.ArrayList;

public class Userinfo {

    private static boolean is_cache = false;
    private static boolean is_updated = false;

    private static String username = "未登陆";
    private static String password = "";
    private static String delivery_address = "";
    private static int phone_number = -1;
    private static boolean is_subscribe = true;
    private static int payment_Method = 0;
    private static ArrayList<String> shop_collection = new ArrayList<>();

    public static boolean isIs_cache() {
        return is_cache;
    }

    public static void setIs_cache(boolean is_cache) {
        Userinfo.is_cache = is_cache;
    }

    public static boolean isIs_updated() {
        return is_updated;
    }

    public static void setIs_updated(boolean is_updated) {
        Userinfo.is_updated = is_updated;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Userinfo.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Userinfo.password = password;
    }

    public static String getDelivery_address() {
        return delivery_address;
    }

    public static void setDelivery_address(String delivery_address) {
        Userinfo.delivery_address = delivery_address;
    }

    public static int getPhone_number() {
        return phone_number;
    }

    public static void setPhone_number(int phone_number) {
        Userinfo.phone_number = phone_number;
    }

    public static boolean isIs_subscribe() {
        return is_subscribe;
    }

    public static void setIs_subscribe(boolean is_subscribe) {
        Userinfo.is_subscribe = is_subscribe;
    }

    public static int getPayment_Method() {
        return payment_Method;
    }

    public static void setPayment_Method(int payment_Method) {
        Userinfo.payment_Method = payment_Method;
    }

    public static ArrayList<String> getShop_collection() {
        return shop_collection;
    }

    public static void setShop_collection(ArrayList<String> shop_collection) {
        Userinfo.shop_collection = shop_collection;
    }


}
