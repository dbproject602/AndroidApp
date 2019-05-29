package com.db.viewmodel;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;
import android.os.Message;

import bean.UserBean;
import service.UserService;
import service.UserServiceImpl;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<UserBean> userBean = new MutableLiveData<>();
    private String username = "";
    private String password = "";
    @SuppressLint("HandlerLeak")
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            userBean.postValue((UserBean) msg.obj);
        }
    };
    public int login(String account, String password) throws Exception{
        UserService userService = new UserServiceImpl();
        userService.login(account,password,handler);
        return 0;
    }

    public LiveData<UserBean> getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean){
        this.userBean =  new MutableLiveData<UserBean>();
        this.userBean.setValue(userBean);
    }

    public String getAccount() {
        return username;
    }

    public void setAccount(String account) {
        this.username = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
