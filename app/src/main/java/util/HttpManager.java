package util;

import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.io.ObjectInputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpManager {
    static String ip_service = "http://10.21.99.25:8080/";

    public static void send(final RequestBody requestbody, final String servlet,final Handler handler) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(ip_service+servlet).post(requestbody).build();
                try {
                    Response response = client.newCall(request).execute();//发送请求
                    ObjectInputStream bitmap = new ObjectInputStream(response.body().byteStream());
                    Object result = null;
                    try {
                        result = bitmap.readObject();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    Message message=new Message();
                    message.obj=result;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    Message message=new Message();
                    message.obj=null;
                    handler.sendMessage(message);
                    e.printStackTrace();
                }

            }
        }).start();
    }

    public static void update(final RequestBody requestbody, final String servlet,final Handler handler) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(ip_service+servlet).post(requestbody).build();
                try {
                    Response response = client.newCall(request).execute();//发送请求
                    Message message=new Message();
                    message.what=1;
                    handler.sendMessage(message);
                } catch (Exception e) {
                    Message message=new Message();
                    message.what=0;
                    handler.sendMessage(message);
                    e.printStackTrace();
                }

            }
        }).start();
    }

}
