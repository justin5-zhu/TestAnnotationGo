package params;

import constants.Constants;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author justin-zhu
 * <p>
 * 2022年10月11日 9:33
 */

public class LoginCookie {
    private Map<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();
    private OkHttpClient client ;
    private String params = "e_rfid=gxaadmin&e_password=123456";

    public void getCookieStore(){
        client = new OkHttpClient.Builder()
                .connectTimeout(1000*10, TimeUnit.SECONDS)
                .cookieJar(new CookieJar() {
                    @Override
                    public void saveFromResponse(@NotNull HttpUrl url, @NotNull List<Cookie> cookies) {
                        cookieStore.put(url, cookies);
                        cookieStore.put(HttpUrl.parse(Constants.LOGIN_URL), cookies);
                        for (Cookie cookie : cookies){
                            System.out.println("cookie Name:"+cookie.name());
                            System.out.println("cookie Path:"+cookie.path());
                        }
                    }
                    @NotNull
                    @Override
                    public List<Cookie> loadForRequest(@NotNull HttpUrl url) {
                        List<Cookie> cookies = cookieStore.get(HttpUrl.parse(Constants.LOGIN_URL));
                        if (cookies==null){
                            System.out.println("没有从request中加载到cookie");
                        }
                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                }).build();
    }

    @Test
    public void loginTest(){
        getCookieStore();
        MediaType type = MediaType.parse(Constants.APPLICATION);
        RequestBody body = RequestBody.create(type, params);
        Request request = new Request.Builder()
                .url(Constants.LOGIN_URL)
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
//            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
