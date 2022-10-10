package cases;

import constants.Constants;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.HttpUtils;

/**
 * @author justin-zhu
 * <p>
 * 2022年10月08日 15:35
 */

public class RegisterCases {
    @Test(dataProvider = "data")
    public void testRegister(String params){
        String url = Constants.LOGIN_URL;
        String method = Constants.GET;
        HttpUtils.call(method, url, params);
    }

    @DataProvider(name = "data")
    public Object[][] data(){
        Object[][] data = {
                {"justin",18},
                {"mary",20},
                {"james",24}
        };
        return data;
    }
}
