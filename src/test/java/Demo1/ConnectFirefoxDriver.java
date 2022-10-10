package Demo1;


import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

/**
 * @author justin-zhu
 * <p>
 * 2022年10月08日 16:54
 */

public class ConnectFirefoxDriver {
    @Test
    public void test2() throws InterruptedException {
        System.setProperty("webdriver.firefox.bin","C:\\Program Files\\Mozilla Firefox\\firefox.exe");
        System.setProperty("webdriver.gecko.driver","src/test/driver/firefoxDriver/geckodriver.exe");
        // 打开firefox浏览器
        FirefoxDriver firefoxDriver = new FirefoxDriver();
        // 打开百度网页
        firefoxDriver.get("https://www.baidu.com/");

        Thread.sleep(3000);
        firefoxDriver.quit();
    }
}
