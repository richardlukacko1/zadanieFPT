package org.example;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.log4testng.Logger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AppTest {
    private AppiumDriver<MobileElement> driver;
    private static final Logger LOGGER = Logger.getLogger(AppTest.class);
    private final URL url;

    {
        try {
            url = new URL("http://localhost:4723/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    static DesiredCapabilities dc = new DesiredCapabilities();

    @BeforeTest
    public void setCaps() throws InterruptedException {
        LOGGER.info("Setting capabilities");
        LOGGER.info("Starting Calculator App...");

        dc.setCapability("deviceName", "Mi Note 10");
        dc.setCapability("udid", "c87eac53");
        dc.setCapability("platformName", "Android");
        dc.setCapability("platformVersion", "11 RKQ1.200826.002");
        dc.setCapability("appPackage", "com.miui.calculator");
        dc.setCapability("appActivity", "com.miui.calculator.cal.CalculatorActivity");
        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");

        driver = new AppiumDriver<>(url, dc);

        TimeUnit.SECONDS.sleep(1);
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/" +
                "android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/" +
                "android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/" +
                "android.widget.LinearLayout[2]/" +
                "android.widget.LinearLayout/android.widget.Button[2]")).click();

        TimeUnit.SECONDS.sleep(1);
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout")).click();
        TimeUnit.SECONDS.sleep(1);
    }

    @Test
    public void testPlus() {
        LOGGER.info("Test + started ...");

        driver.findElement(By.id("com.miui.calculator:id/btn_2_s")).click();
        driver.findElement(By.id("com.miui.calculator:id/btn_plus_s")).click();
        driver.findElement(By.id("com.miui.calculator:id/btn_8_s")).click();
        driver.findElement(By.id("com.miui.calculator:id/btn_equal_s")).click();

        String result =driver.findElement(By.id("com.miui.calculator:id/result")).getText();
        Assert.assertEquals(result, "= 10");
    }

    @Test
    public void testMinus() {
        LOGGER.info("Test - started ...");

        driver.findElement(By.id("com.miui.calculator:id/btn_2_s")).click();
        driver.findElement(By.id("com.miui.calculator:id/btn_minus_s")).click();
        driver.findElement(By.id("com.miui.calculator:id/btn_8_s")).click();
        driver.findElement(By.id("com.miui.calculator:id/btn_equal_s")).click();

        String result = driver.findElement(By.id("com.miui.calculator:id/result")).getText();
        Assert.assertEquals(result, "= -6");
    }

    @Test
    public void testMulti() {
        LOGGER.info("Test * started ...");

        driver.findElement(By.id("com.miui.calculator:id/btn_2_s")).click();
        driver.findElement(By.id("com.miui.calculator:id/btn_mul_s")).click();
        driver.findElement(By.id("com.miui.calculator:id/btn_8_s")).click();
        driver.findElement(By.id("com.miui.calculator:id/btn_equal_s")).click();

        String result = driver.findElement(By.id("com.miui.calculator:id/result")).getText();
        Assert.assertEquals(result, "= 16");
    }

    @Test
    public void testDivision() {
        LOGGER.info("Test / started ...");

        driver.findElement(By.id("com.miui.calculator:id/btn_2_s")).click();
        driver.findElement(By.id("com.miui.calculator:id/btn_div_s")).click();
        driver.findElement(By.id("com.miui.calculator:id/btn_8_s")).click();
        driver.findElement(By.id("com.miui.calculator:id/btn_equal_s")).click();

        String result = driver.findElement(By.id("com.miui.calculator:id/result")).getText();
        Assert.assertEquals(result, "= 0,25");
    }

    @AfterMethod
    public void afterEachTest() {
        driver.findElement(By.id("com.miui.calculator:id/btn_c_s")).click();
        LOGGER.info("Clearing Calculator result box.");
    }

    @AfterSuite
    public void afterAll() {
        LOGGER.info("Tests completed.");
        LOGGER.info("Turning driver off.");
        driver.quit();
    }

}