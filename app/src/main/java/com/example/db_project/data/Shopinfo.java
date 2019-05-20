package com.example.db_project.data;

import java.util.ArrayList;

public class Shopinfo {
    private String name;
    private double x_loc;
    private double y_loc;
    private double rate;
    private String location;
    private int phone_number;
   // private Url ImageUrl;

    public Shopinfo(String name, double x_loc, double y_loc, double rate, String location, int phone_number) {
        this.name = name;
        this.x_loc = x_loc;
        this.y_loc = y_loc;
        this.rate = rate;
        this.location = location;
        this.phone_number = phone_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getX_loc() {
        return x_loc;
    }

    public void setX_loc(double x_loc) {
        this.x_loc = x_loc;
    }

    public double getY_loc() {
        return y_loc;
    }

    public void setY_loc(double y_loc) {
        this.y_loc = y_loc;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }
}

