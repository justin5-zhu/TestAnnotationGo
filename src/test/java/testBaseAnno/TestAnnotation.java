package testBaseAnno;

import org.testng.Assert;
import org.testng.annotations.*;

/**
 * @author justin-zhu
 * <p>
 * 2022年10月04日 22:50
 */

public class TestAnnotation {

    @Test(groups = "group1")
    public void test1(){
        System.out.println("test1 from group1");
        Assert.assertTrue(true);
    }
    @Test(groups = "group1")
    public void test11(){
        System.out.println("test11 from group1");
        Assert.assertTrue(true);
    }
    @Test(groups = "group2")
    public void test2(){
        System.out.println("test2 from group2");
        Assert.assertTrue(true);
    }
    // 将会在testng定义的xml根元素里面的所有执行之前运行。
    @BeforeSuite
    public void testBeforeSuite(){
        System.out.println("This is a beforeSuite !");
        Assert.assertTrue(true);
    }

    // 将会在testng定义的xml根元素里面的所有执行之后运行。
    @AfterSuite
    public void testAfterSuite(){
        System.out.println("This is a AfterSuite !");
        Assert.assertTrue(true);
    }

    // 将会在一个元素(xml中的<test/>)定义的所有里面所有的测试方法执行之前运行。
    @BeforeTest
    public void testBeforeTest(){
        System.out.println("This is a beforeTest !");
        Assert.assertTrue(true);
    }

    // 将会在一个元素(xml中的<test/>)定义的所有里面所有的测试方法执行之后运行。
    @AfterTest
    public void testAfterTest(){
        System.out.println("This is a afterTest !");
        Assert.assertTrue(true);
    }
    // 将会在当前测试类的第一个测试方法执行之前运行。
    @BeforeClass
    public void testBeforeClass(){
        System.out.println("This is a beforeClass !");
        Assert.assertTrue(true);
    }

    // 将会在当前测试类的最后一个测试方法执行之后运行。
    @AfterClass
    public void testAfterBeforeSuite(){
        System.out.println("This is a afterSuite !");
        Assert.assertTrue(true);
    }

    // 将会在当前测试类的每一个测试方法执行之前运行。
    @BeforeMethod
    public void testBeforeMethod(){
        System.out.println("This is a beforeMethod !");
        Assert.assertTrue(true);
    }

    // 将会在当前测试类的每一个测试方法执行之后运行。
    @AfterMethod
    public void testAfterMethod(){
        System.out.println("This is a afterMethod !");
        Assert.assertTrue(true);
    }

    // 只对某一分组生效
    @BeforeGroups(groups = "group1")
    public void testBeforeGroup(){
        System.out.println("This is a beforeGroup !");
        Assert.assertTrue(true);
    }
    @AfterGroups(groups = "group2")
    public void testAfterGroup(){
        System.out.println("This is a afterGroup !");
        Assert.assertTrue(true);
    }
}
