package dao;

import android.os.Handler;

import bean.UserinfoBean;

public interface UserinfoDao {
    void login(String account,String password, Handler handler) throws Exception;
    void register(UserinfoBean userinfoBean, Handler handler) throws Exception;
}
