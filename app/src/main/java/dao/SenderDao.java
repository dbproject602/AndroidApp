package dao;

import android.os.Handler;

public interface SenderDao {
    void fetchSender(int orderId, Handler handler) throws Exception;

}
