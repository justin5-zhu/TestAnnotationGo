package Demo1;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

/**
 * @author justin-zhu
 * <p>
 * 2022年10月08日 16:54
 */

public class ConnectChromeDriver {

    @Test
    public void test1() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","src/test/driver/chromeDriver/chromedriver.exe");
        // 打开Chrome浏览器
        ChromeDriver chromeDriver = new ChromeDriver();
        // 打开百度网页
        chromeDriver.get("https://www.baidu.com/");
        Thread.sleep(3000);
        chromeDriver.quit();
    }
}
