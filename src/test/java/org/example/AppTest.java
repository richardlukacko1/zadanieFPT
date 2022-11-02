package org.example;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.testng.log4testng.Logger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AppTest {
    static AppiumDriver<MobileElement> driver;
    private static final Logger LOGGER = Logger.getLogger(AppTest.class);
    static URL url;

    static {
        try {
            url = new URL("http://localhost:4723/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    static DesiredCapabilities dc = new DesiredCapabilities();

    @BeforeTest
    public void setCaps() throws InterruptedException {
        Reporter.log("Setting capabilities");
        LOGGER.info("Setting capabilities");
        Reporter.log("Starting Calculator App...");
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
        MobileElement agreeButton = driver.findElement(By.id("android:id/button1"));
        TimeUnit.SECONDS.sleep(1);
        agreeButton.click();
        TimeUnit.SECONDS.sleep(1);
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout")).click();
    }

    @Test
    public void testPlus() throws InterruptedException {
        Reporter.log("Test + started ...");
        LOGGER.info("Test + started ...");

        TimeUnit.SECONDS.sleep(1);
        MobileElement two = driver.findElement(By.id("com.miui.calculator:id/btn_2_s"));
        two.click();
        TimeUnit.SECONDS.sleep(1);
        MobileElement plus = driver.findElement(By.id("com.miui.calculator:id/btn_plus_s"));
        plus.click();
        TimeUnit.SECONDS.sleep(1);
        MobileElement eight = driver.findElement(By.id("com.miui.calculator:id/btn_8_s"));
        eight.click();
        TimeUnit.SECONDS.sleep(1);
        MobileElement equals = driver.findElement(By.id("com.miui.calculator:id/btn_equal_s"));
        equals.click();
        TimeUnit.SECONDS.sleep(1);
        MobileElement resultArea = driver.findElement(By.id("com.miui.calculator:id/result"));
        String result = resultArea.getText();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(result, "= 10");
    }

    @Test
    public void testMinus() throws InterruptedException {
        Reporter.log("Test - started ...");
        LOGGER.info("Test - started ...");

        TimeUnit.SECONDS.sleep(1);
        MobileElement two = driver.findElement(By.id("com.miui.calculator:id/btn_2_s"));
        two.click();
        TimeUnit.SECONDS.sleep(1);
        MobileElement minus = driver.findElement(By.id("com.miui.calculator:id/btn_minus_s"));
        minus.click();
        TimeUnit.SECONDS.sleep(1);
        MobileElement eight = driver.findElement(By.id("com.miui.calculator:id/btn_8_s"));
        eight.click();
        TimeUnit.SECONDS.sleep(1);
        MobileElement equals = driver.findElement(By.id("com.miui.calculator:id/btn_equal_s"));
        equals.click();
        TimeUnit.SECONDS.sleep(1);
        MobileElement resultArea = driver.findElement(By.id("com.miui.calculator:id/result"));
        String result = resultArea.getText();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(result, "= -6");
    }

    @Test
    public void testMulti() throws InterruptedException {
        Reporter.log("Test * started ...");
        LOGGER.info("Test * started ...");

        TimeUnit.SECONDS.sleep(1);
        MobileElement two = driver.findElement(By.id("com.miui.calculator:id/btn_2_s"));
        two.click();
        TimeUnit.SECONDS.sleep(1);
        MobileElement multiply = driver.findElement(By.id("com.miui.calculator:id/btn_mul_s"));
        multiply.click();
        TimeUnit.SECONDS.sleep(1);
        MobileElement eight = driver.findElement(By.id("com.miui.calculator:id/btn_8_s"));
        eight.click();
        TimeUnit.SECONDS.sleep(1);
        MobileElement equals = driver.findElement(By.id("com.miui.calculator:id/btn_equal_s"));
        equals.click();
        TimeUnit.SECONDS.sleep(1);
        MobileElement resultArea = driver.findElement(By.id("com.miui.calculator:id/result"));
        String result = resultArea.getText();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(result, "= 16");
    }

    @Test
    public void testDivision() throws InterruptedException {
        Reporter.log("Test / started ...");
        LOGGER.info("Test / started ...");

        TimeUnit.SECONDS.sleep(1);
        MobileElement two = driver.findElement(By.id("com.miui.calculator:id/btn_2_s"));
        two.click();
        TimeUnit.SECONDS.sleep(1);
        MobileElement divide = driver.findElement(By.id("com.miui.calculator:id/btn_div_s"));
        divide.click();
        TimeUnit.SECONDS.sleep(1);
        MobileElement eight = driver.findElement(By.id("com.miui.calculator:id/btn_8_s"));
        eight.click();
        TimeUnit.SECONDS.sleep(1);
        MobileElement equals = driver.findElement(By.id("com.miui.calculator:id/btn_equal_s"));
        equals.click();
        TimeUnit.SECONDS.sleep(1);
        MobileElement resultArea = driver.findElement(By.id("com.miui.calculator:id/result"));
        String result = resultArea.getText();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(result, "= 0,25");
    }

    @AfterMethod
    public void afterEachTest() throws InterruptedException {
        MobileElement clear = driver.findElement(By.id("com.miui.calculator:id/btn_c_s"));
        TimeUnit.SECONDS.sleep(1);
        clear.click();
        Reporter.log("Clearing Calculator result box.");
        LOGGER.info("Clearing Calculator result box.");
    }

    @AfterSuite
    public void afterAll() throws InterruptedException {
            TimeUnit.SECONDS.sleep(1);
            Reporter.log("Tests completed.");
            LOGGER.info("Tests completed.");
            Reporter.log("Turning driver off.");
            LOGGER.info("Turning driver off.");
            driver.quit();
    }

}