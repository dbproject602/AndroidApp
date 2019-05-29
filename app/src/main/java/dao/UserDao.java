package dao;

import android.os.Handler;

import bean.UserBean;

public interface UserDao {
    void fetchUser(String userName,String password, Handler handler) throws Exception;
    void addUser(UserBean userBean, Handler handler) throws Exception;
    int updateUser(UserBean userBean,Handler handler) throws Exception;
    int deleteUserbyId(int userId) throws Exception;
}
