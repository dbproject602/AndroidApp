package bean;

import java.io.Serializable;
import java.util.ArrayList;

public class SenderBean implements Serializable {
    private int senderId;
    private String senderName;
    private String password;
    private int state; // 0 waiting 1 sending -1 resting
    private String telephone;
    private double reputation;
    private ArrayList<Integer> shopIdItems;

    public SenderBean(int senderId, String senderName, String password,
                      int state, String telephone, double reputation, ArrayList<Integer> shopIdItems) {
        this.senderId = senderId;
        this.senderName = senderName;
        this.password = password;
        this.state = state;
        this.telephone = telephone;
        this.reputation = reputation;
        this.shopIdItems = shopIdItems;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public double getReputation() {
        return reputation;
    }

    public void setReputation(double reputation) {
        this.reputation = reputation;
    }

    public ArrayList<Integer> getShopIdItems() {
        return shopIdItems;
    }

    public void setShopIdItems(ArrayList<Integer> shopIdItems) {
        this.shopIdItems = shopIdItems;
    }
}
