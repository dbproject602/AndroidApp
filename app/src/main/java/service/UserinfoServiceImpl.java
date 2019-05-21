package service;

import android.os.Handler;

import dao.UserinfoDao;
import dao.UserinfoDaoImpl;

public class UserinfoServiceImpl implements UserinfoService {
    UserinfoDao userinfoDao = new UserinfoDaoImpl();
    @Override
    public void login(String account, String password, Handler handler) throws Exception {
        userinfoDao.login(account,password,handler);
    }
}
