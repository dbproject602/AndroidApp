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

public class RegisterViewModel extends ViewModel {
    private MutableLiveData<UserBean> userBean = new MutableLiveData<>();
    @SuppressLint("HandlerLeak")
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    public LiveData<UserBean> getUserBean() {
        return userBean;
    }
    public void setUserBean(int userId,String userName,String password,String telephone,String address,String name){
        UserBean userBean = new UserBean(userId,userName,password,telephone,address,name);
        this.userBean =  new MutableLiveData<UserBean>();
        this.userBean.setValue(userBean);
    }
    public int register() throws Exception{
        UserService userService = new UserServiceImpl();
        userService.register(userBean.getValue(),handler);
        return 0;
    }

}
