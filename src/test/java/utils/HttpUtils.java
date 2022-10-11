package utils;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import static constants.Constants.APPLICATION;

/**
 * @author justin-zhu
 * <p>
 * 2022年10月08日 15:05
 */

public class HttpUtils {
    private static OkHttpClient client;

    public static Response get(String url, String params){
        // 1、创建OKHttpClient
        client = new OkHttpClient();
        // 2、创建Request
        Request request = new Request.Builder()
                .url(url+"?"+params)
                .get()
                .build();
        Response response = null;
        // 3、使用client发送请求
        try {
            response = client.newCall(request).execute();
//            System.out.println(response.code());
//            System.out.println(response.headers());
//            System.out.println(response.body().string());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static Response post(String url, String params){
        client = new OkHttpClient();
        MediaType type = MediaType.parse(APPLICATION);
        RequestBody body = RequestBody.create(type, params);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = null;
        try {
            //
            response = client.newCall(request).execute();
//            System.out.println(response.code());
//            System.out.println(response.headers());
//            System.out.println(response.body().string());
            } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static boolean postAsync(String url, String params){
        OkHttpClient client = new OkHttpClient();
        MediaType type = MediaType.parse(APPLICATION);
        RequestBody body = RequestBody.create(type, params);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        // 异步
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                // NOT UI Thread
                if (response.isSuccessful()){
                    System.out.println(response.code());
                }
            }
        });
        return true;
    }

    public static Object call(String method,String url, String params) {
        if ("get".equalsIgnoreCase(method)){
            return get(url,params);
        }else if ("post".equalsIgnoreCase(method)){
            return post(url, params);
        }else if ("postAsync".equalsIgnoreCase(method)){
             return postAsync(url, params);
        }else{
            return "非法的请求方式！";
        }
    }
}
