package service;

import android.os.Handler;

import bean.UserinfoBean;

public interface UserinfoService {
    void login(String account,String password, Handler handler) throws Exception;
    void register(UserinfoBean userinfoBean, Handler handler) throws Exception;

}
