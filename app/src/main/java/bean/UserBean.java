package bean;

import java.io.Serializable;

public class UserBean implements Serializable {

    private String userId;
    private String account;
    private String password;
    private String username;
    private int phone;
    private boolean is_subscribe;

    public UserBean(String userId, String account, String password, int phone, String username, boolean is_subscribe) {
        this.userId = userId;
        this.account = account;
        this.password = password;
        this.phone = phone;
        this.username = username;
        this.is_subscribe = is_subscribe;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public boolean isIs_subscribe() {
        return is_subscribe;
    }

    public void setIs_subscribe(boolean is_subscribe) {
        this.is_subscribe = is_subscribe;
    }

}
