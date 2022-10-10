package cases;

import constants.Constants;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.HttpUtils;
import utils.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @author justin-zhu
 * <p>
 * 2022年10月08日 15:35
 */

public class LoginCases {
    // 存放cookie
    private static String cookie = null;



    @DataProvider(name = "data")
    public Object[][] data(){
        return new Object[][]{
                {"username=&password=123123"},
                {"username=13212312312&password="},
                {"username=13212312312&password=123123"},
                {"username=13212312312&password=123456"},
                {"e_rfid=gxaadmin&e_password=123456"}
        };
    }

    @Test(dataProvider = "data")
    public void loginSync(String params) throws IOException {
        String url = Constants.LOGIN_URL;
        String method = Constants.POST;
//        String params ="e_rfid=gxaadmin&e_password=123456";
        Response response = (Response) HttpUtils.call(method, url, params);
        if (response.isSuccessful()){
            List<String> cookies = response.headers().values("Set-Cookie");
            if (!cookies.isEmpty()){
                String session = cookies.get(0);
                if (!StringUtils.isNullOrEmpty(session)){
                    int size = session.length();
                    int i = session.indexOf(";");
                    if (i<size&&i>=0){
                        cookie = session.substring(0, i);
                    }
                }
            }
        }
        String responseStr = Objects.requireNonNull(response.body()).string();
        System.out.println(response.headers());
        System.out.println(cookie);
        System.out.println(responseStr);
        Assert.assertEquals(response.code(),200);
        Assert.assertTrue(responseStr.contains("登陆成功"),"响应中不包含“登陆成功”");
    }

    @Test
    public void loginASync() throws IOException {
        String url = Constants.LOGIN_URL;
        String method = Constants.POST;
        String params ="e_rfid=gxaadmin&e_password=123456";
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUtils.call(method, url, params);
            }
        }).start();
    }
}
