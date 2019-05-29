package dao;

import android.os.Handler;
import android.util.Base64;

import java.util.Arrays;

import bean.UserBean;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import util.HttpManager;
import util.ObjToBytes;

public class UserDaoImpl implements UserDao {

    @Override
    public void fetchUser(String userName, String password, Handler handler) throws Exception {
        String servlet = "LoginServlet";
        RequestBody requestBody = new FormBody.Builder().add("username",userName).add("password",password).build();
        HttpManager.send(requestBody,servlet,handler);
    }
    @Override
    public void addUser(UserBean userBean, Handler handler) throws Exception{
        String servlet = "RegisterServlet";
        byte[] bytes = ObjToBytes.objtobytes(userBean);
        String str = Base64.encodeToString(bytes,Base64.DEFAULT);
        RequestBody requestBody = new FormBody.Builder().add("userBean",str).build();
        HttpManager.update(requestBody,servlet,handler);
    }

    @Override
    public int updateUser(UserBean userBean,Handler handler) throws Exception {
        String servlet = "UpdateUserServlet";
        byte[] bytes = ObjToBytes.objtobytes(userBean);
        String str = Base64.encodeToString(bytes,Base64.DEFAULT);
        RequestBody requestBody = new FormBody.Builder().add("userBean",str).build();
        HttpManager.update(requestBody,servlet,handler);
        return 0;
    }

    @Override
    public int deleteUserbyId(int userId) throws Exception {
        return 0;
    }

}
