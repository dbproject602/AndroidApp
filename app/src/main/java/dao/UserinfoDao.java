package dao;

import android.os.Handler;

public interface UserinfoDao {
    void login(String account,String password, Handler handler) throws Exception;
    void register(String account,String password,Handler handler) throws Exception;
}
