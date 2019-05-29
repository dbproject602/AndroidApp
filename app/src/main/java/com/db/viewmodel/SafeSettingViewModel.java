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

public class SafeSettingViewModel extends ViewModel {
    private static MutableLiveData<UserBean> userBean = new MutableLiveData<>();
    private MutableLiveData<Integer> flag = new MutableLiveData<>();
    @SuppressLint("HandlerLeak")
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int flag = (int) msg.what;
            setFlag(flag);
        }
    };
    public LiveData<UserBean> getUserBean() {
        return userBean;
    }
    public LiveData<Integer> getFlag() {
        return flag;
    }
    public void setUserBean(String password,String telephone,String address,String name){
        userBean.getValue().setPassword(password);
        userBean.getValue().setTelephone(telephone);
        userBean.getValue().setAddress(address);
        userBean.getValue().setName(name);
    }
    public static void setUserBean(UserBean user){
        userBean.setValue(user);
    }
//    public UserBean getUserBean(){return userBean;};
    public void setFlag(int flag){
        this.flag.setValue(flag);
    }
    public int update() throws Exception{
        UserService userService = new UserServiceImpl();
        userService.update(userBean.getValue(),handler);
        return 0;
    }
}
