package params;

import org.testng.annotations.DataProvider;

/**
 * @author justin-zhu
 * <p>
 * 2022年10月09日 16:31
 */

public class GoodTypeParams {
    @DataProvider(name = "testGoodTypeList")
    public Object[][] data(){
        Object[][] data = {
                {"page=2&pageSize=3&keyWords=00000000&operate=adminQuery"},
                {"nowPage=1&pageSize=3&keyWords=00000000&operate=eventQuery"},
                {"nowPage=2&pageSize=3&keyWords=00000000&operate=adminQuery"},
                {"nowPage=1&pageSize=3&keyWords=00000000&operate=adminQuery"},
                {"page=1&pageSize=3&keyWords=00000000&operate=adminQuery"},
                {"page=1&pageSize=3&keyWords=00010000&operate=adminQuery"},
                {"page=2&pageSize=3&keyWords=00010000&operate=adminQuery"}
        };
        return data;
    }
}
