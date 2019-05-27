package dao;
import android.os.Handler;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import util.HttpManager;

public class FoodDaoImpl implements FoodDao {
    @Override
    public void fetchFoodList(String shopid,Handler handler) throws Exception {
        String servlet = "FetchFoodServlet";

        RequestBody requestBody = new FormBody.Builder().add("shopid",shopid).build();
        HttpManager.send(requestBody,servlet,handler);
    }
}
