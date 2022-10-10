package cases;

import com.sun.org.glassfish.gmbal.Description;
import constants.Constants;
import okhttp3.Response;
import org.ietf.jgss.Oid;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.HttpUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;


/**
 * @author justin-zhu
 * <p>
 * 2022年10月09日 11:00
 */

public class GoodTypeFromFile {

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

     private void writeFromStr(){
        File file = new File("src/test/download/goodTypeList.txt");
        String[] data = {
                "page=2&pageSize=3&keyWords=00000000&operate=adminQuery",
                "nowPage=1&pageSize=3&keyWords=00000000&operate=eventQuery",
                "nowPage=2&pageSize=3&keyWords=00000000&operate=adminQuery",
                "nowPage=1&pageSize=3&keyWords=00000000&operate=adminQuery",
                "page=1&pageSize=3&keyWords=00000000&operate=adminQuery",
                "page=1&pageSize=3&keyWords=00010000&operate=adminQuery",
                "page=2&pageSize=3&keyWords=00010000&operate=adminQuery"};
        try {
            OutputStream os = new FileOutputStream(file);
            OutputStreamWriter writer = new OutputStreamWriter(os, StandardCharsets.UTF_8);
            for (String str : data){
                writer.append(str).append("\r\n");
            }
            writer.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从文件中读取数据并存入数组
     *
     */
    @Test
    private void ReadAfterWrite(){
//        写入数据并保存为TXT文本
//        GoodTypeFromFile good = new GoodTypeFromFile();
//        good.writeFromStr();
        FileReader fr;
        List<String> data = null;
        try {
            fr = new FileReader("src/test/download/goodTypeList.txt");

            BufferedReader reader = new BufferedReader(fr);
            while (reader.ready()){
//                System.out.println(reader.readLine());
                    data.add(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
