package service;

import android.os.Handler;

public interface UserinfoService {
    void login(String account,String password, Handler handler) throws Exception;
    void register(String account,String password,Handler handler) throws Exception;

}
