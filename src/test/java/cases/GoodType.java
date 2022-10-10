package cases;

import com.sun.org.glassfish.gmbal.Description;
import constants.Constants;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.HttpUtils;
import java.io.IOException;


/**
 * @author justin-zhu
 * <p>
 * 2022年10月09日 11:00
 */

public class GoodType {

    @DataProvider
    public static Object[][] testGoodsTypeSave() {
        return new Object[][]{
                {"gt_name=佐料10&gt_descript=各类酱油 豆油 豆瓣酱&gt_state=0&gt_level=2&gt_pno=00000000"}

        };
    }

    @DataProvider
    public static Object[][] testLockGoods() {
        return new Object[][]{
                {"g_no=00010002000001&g_state=1"},
                {"g_no=00010002000001&g_state=0"}
        };
    }

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

    @DataProvider
    public static Object[][] testGoodsList() {
        return new Object[][]{
                {"page=1&pageSize=3&keyWords=00010003"},
                {"nowPage=1&pageSize=3&keyWords=00010002"},
                {"keyWords=00010002"}
        };
    }

    @Description("测试查看商品类别列表接口")
    @Test(dataProvider = "testGoodTypeList")
    public void testGoodTypeList(String params){
        String url = Constants.GOODTYPE;
        String method = Constants.GET;
        Response response = (Response) HttpUtils.call(method, url, params);
        try {
            Assert.assertTrue(response.body().string().contains("佐料"),"response.body()中不包含“佐料”");
            Assert.assertEquals(response.code(),200);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Description("测试按商品类别查看商品")
    @Test(dataProvider = "testGoodsList")
    public void testGoodsList(String params){
        String url = Constants.GOODS;
        String method = Constants.GET;
        Response response = (Response) HttpUtils.call(method, url, params);
        try {
            Assert.assertTrue(response.body().string().contains("百事可乐"),"response.body()中不包含“百事可乐”");
            Assert.assertEquals(response.code(),200);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Description("测试增加商品类别")
    @Test(dataProvider = "testGoodsTypeSave")
    public void testGoodsTypeSave(String params){
        String url = Constants.GOODSTYPESAVE;
        String method = Constants.POST;
        Response response = (Response) HttpUtils.call(method, url, params);
        try {
            Assert.assertTrue(response.body().string().contains("添加商品类别失败,该类别已存在"),"response.body()中不包含“添加商品类别失败...”");
            Assert.assertEquals(response.code(),200);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Description("测试锁定/解锁商品")
    @Test(dataProvider = "testLockGoods")
    public void testLockGoods(String params) throws IOException {
        String url = Constants.LOOKGOODS;
        String method = Constants.POST;
        Response response = (Response) HttpUtils.call(method, url, params);
        Assert.assertEquals(response.code(),200);
        System.out.println(response.body().string());

    }
}
