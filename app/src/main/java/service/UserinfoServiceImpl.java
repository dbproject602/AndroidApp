package service;

import android.os.Handler;

import bean.UserinfoBean;
import dao.UserinfoDao;
import dao.UserinfoDaoImpl;

public class UserinfoServiceImpl implements UserinfoService {
    UserinfoDao userinfoDao = new UserinfoDaoImpl();
    @Override
    public void login(String account, String password, Handler handler) throws Exception {
        userinfoDao.login(account,password,handler);
    }
    @Override
    public void register(UserinfoBean userinfoBean, Handler handler) throws Exception{
        userinfoDao.register(userinfoBean ,handler);
    }
}
