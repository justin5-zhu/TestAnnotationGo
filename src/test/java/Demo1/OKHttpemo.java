package Demo1;

import okhttp3.*;
import org.testng.annotations.Test;
import java.io.IOException;

/**
 * @author justin-zhu
 * <p>
 * 2022年10月08日 14:23
 */

public class OKHttpemo {
    public static void main(String[] args) throws IOException {
        String url = "http://test.lemonban.com/ningmengban/app/login/login.html";
        // 1、创建OKHttpClient
        OkHttpClient client = new OkHttpClient();
        // 2、构建request
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        // 3、使用client发生请求，返回一个响应
        Response response = client.newCall(request).execute();
        System.out.println(response.code());
        System.out.println(response.headers());
        // http的版本，例：http/1.1
        System.out.println(response.cacheControl());
        System.out.println(response.protocol());

    }

    @Test
    public void oKHttpPost() throws IOException {
        String url = "http://test.lemonban.com/ningmengban/mvc/user/login.json";
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType,"username=12312312312&password=123123");
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.code());
        System.out.println(response.headers());
        System.out.println(response.body().string());
    }
}
