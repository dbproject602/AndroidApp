package bean;

import java.io.Serializable;

public class UserinfoBean implements Serializable {
    private int userid;
    private String account;
    private String password;
    private String username;
    private int phone;
    private boolean is_subscribe;


    public UserinfoBean(int userid, String account, String password, String username, int phone, boolean is_subsubscribe) {
        this.userid = userid;
        this.account = account;
        this.password = password;
        this.username = username;
        this.phone = phone;
        this.is_subscribe = is_subsubscribe;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
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

    public void setIs_subscribe(boolean is_subsubscribe) {
        this.is_subscribe = is_subsubscribe;
    }

    public int getId() {
        return userid;
    }
    public void setId(int id) {
        this.userid = id;
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
}
