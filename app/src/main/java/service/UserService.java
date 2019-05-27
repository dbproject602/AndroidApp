package service;

import android.os.Handler;

import bean.UserBean;

public interface UserService {
    void login(String username,String password, Handler handler) throws Exception;
    void register(UserBean userBean, Handler handler) throws Exception;
    int update(UserBean userBean,Handler handler) throws Exception;
}
