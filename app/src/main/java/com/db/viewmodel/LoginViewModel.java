package com.db.viewmodel;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.db.view.LoginActivity;

import java.util.ArrayList;

import bean.FoodBean;
import bean.UserinfoBean;
import service.UserinfoService;
import service.UserinfoServiceImpl;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<UserinfoBean> userinfo = new MutableLiveData<>();
    private String account = "";
    private String password = "";
    @SuppressLint("HandlerLeak")
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            userinfo.postValue((UserinfoBean) msg.obj);
        }
    };
    public LoginViewModel(){

    }
    public int login(String account, String password) throws Exception{
        UserinfoService userinfoService = new UserinfoServiceImpl();
        userinfoService.login(account,password,handler);

        return 0;
    }

    public LiveData<UserinfoBean> getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(UserinfoBean userinfo){
        this.userinfo =  new MutableLiveData<UserinfoBean>();
        this.userinfo.setValue(userinfo);
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
