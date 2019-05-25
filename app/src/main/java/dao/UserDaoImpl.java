package dao;

import android.os.Handler;

import bean.UserBean;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import util.HttpManager;
import util.ObjToBytes;

public class UserDaoImpl implements UserDao {

    @Override
    public void fetchUser(String username, String password, Handler handler) throws Exception {
        String servlet = "LoginServlet";
        RequestBody requestBody = new FormBody.Builder().add("username",username).add("password",password).build();
        HttpManager.send(requestBody,servlet,handler);
    }
    @Override
    public void addUser(UserBean userBean, Handler handler) throws Exception{
        String servlet = "RegisterServlet";
        byte[] bytes = null;
        bytes = ObjToBytes.objtobytes(userBean);
        String str = new String(bytes,"ISO-8859-1");
        RequestBody requestBody = new FormBody.Builder().add("userBean",str).build();
        HttpManager.send(requestBody,servlet,handler);
    }

    @Override
    public int updateUser(UserBean userBean) throws Exception {
        return 0;
    }

    @Override
    public int deleteUserbyId(int userId) throws Exception {
        return 0;
    }

}
