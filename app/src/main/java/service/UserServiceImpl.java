package service;

import android.os.Handler;

import bean.UserBean;
import dao.UserDao;
import dao.UserDaoImpl;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();
    @Override
    public void login(String username, String password, Handler handler) throws Exception {
        userDao.fetchUser(username,password,handler);
    }
    @Override
    public void register(UserBean userBean, Handler handler) throws Exception{
        userDao.addUser(userBean ,handler);
    }
}
