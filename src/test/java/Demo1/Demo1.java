package Demo1;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * @author justin-zhu
 * <p>
 * 2022年10月08日 11:29
 */

public class Demo1 {

    @Test(dataProvider = "data")
    public void test1(String name, int age){
        System.out.println(name + ": " + age);
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

    @Test
    @Parameters({"para2","para2"})
    public void test2(String para1, String para2){
        System.out.println(para1);
        System.out.println(para2);
        Assert.assertEquals(para1, para2);

    }
}
