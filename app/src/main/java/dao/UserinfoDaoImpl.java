package dao;

import android.os.Handler;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import util.HttpManager;

public class UserinfoDaoImpl implements UserinfoDao {

    @Override
    public void login(String account, String password, Handler handler) throws Exception {
        String servlet = "LoginServlet";
        RequestBody requestBody = new FormBody.Builder().add("account",account).add("password",password).build();
        HttpManager.send(requestBody,servlet,handler);
    }
    @Override
    public void register(String account,String password,Handler handler) throws Exception{
        String servlet = "RegisterServlet";
        RequestBody requestBody = new FormBody.Builder().add("account",account).add("password",password).build();
        HttpManager.send(requestBody,servlet,handler);
    }
}
