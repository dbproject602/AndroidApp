package dao;

import android.os.Handler;
import bean.UserinfoBean;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import util.HttpManager;
import util.ObjToBytes;

public class UserinfoDaoImpl implements UserinfoDao {

    @Override
    public void login(String account, String password, Handler handler) throws Exception {
        String servlet = "LoginServlet";
        RequestBody requestBody = new FormBody.Builder().add("account",account).add("password",password).build();
        HttpManager.send(requestBody,servlet,handler);
    }
    @Override
    public void register(UserinfoBean userinfoBean, Handler handler) throws Exception{
        String servlet = "RegisterServlet";
        byte[] bytes = null;
        bytes = ObjToBytes.objtobytes(userinfoBean);
        String str = new String(bytes,"ISO-8859-1");
        RequestBody requestBody = new FormBody.Builder().add("userinfoBean",str).build();
        HttpManager.send(requestBody,servlet,handler);
    }
}
