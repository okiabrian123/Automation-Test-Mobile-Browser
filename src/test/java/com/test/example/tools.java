package com.test.example;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class tools {

    public static AppiumDriver  SetupDriver(){
        AppiumDriver driver = null;
        DesiredCapabilities caps = new DesiredCapabilities();
        String platform = System.getProperty("platform");

        List<String> properties = Arrays.asList(
                "appium:platform",
                "appium:automationName",
                "appium:platformVersion",
                "appium:deviceName",
                "browserName"
        );

        // Lakukan perulangan untuk mengambil properti-properti tersebut
        for (String property : properties) {
            String property2=property;
            if(property.contains(":")) property2= property.split(":")[1];
            String value = System.getProperty(property2);

            if (value != null) {
                System.out.println(property+" : "+ value);
                caps.setCapability(property,value);
            }else{
                System.out.println("Need input :"+property);
                return null;
            }
        }


        try {
            if ("Android".equalsIgnoreCase(platform)) {
                String driverPath = System.getProperty("chromedriverPath");
                System.out.println("chromedriverPath version : "+ driverPath);
                String projectDir = System.getProperty("user.dir");
                if (!driverPath.startsWith("/")) { // Jika tidak dimulai dengan '/'
                    driverPath = projectDir + "/driver/" + driverPath+"/chromedriver"; // Menggabungkan dengan projectDir
                }

                caps.setCapability("appium:chromedriverExecutable", driverPath);
                driver = new AndroidDriver(new URL("http://0.0.0.0:4723"), caps);
                // Lakukan pengujian untuk Android
            } else if ("iOS".equalsIgnoreCase(platform)) {
                String useSimulator = System.getProperty("useSimulator");
                System.out.println("safari:useSimulator : "+ useSimulator);
                caps.setCapability("safari:useSimulator", useSimulator);
                driver = new IOSDriver(new URL("http://0.0.0.0:4723"), caps);
                // Lakukan pengujian untuk iOS
            }
            System.out.println("-------------");
            System.out.println("Running Test");
            System.out.println("-------------");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }

    public static WebDriverWait accessLoginPage(int seconds,WebDriver driver){
        driver.get("https://www.saucedemo.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        return wait;
    }

    public static void fillLoginForm(WebDriver driver, String usernameXPath, String Username, String passwordXPath, String Password, String buttonLoginXpath){
        driver.findElement(By.xpath(usernameXPath)).sendKeys(Username);
        driver.findElement(By.xpath(passwordXPath)).sendKeys(Password);
        driver.findElement(By.xpath(buttonLoginXpath)).click();
    }

    public static void checking(AppiumDriver driver, WebDriverWait wait, String waitUntilXPath, String actualXPath, String Expected){
        try{
            wait.until((ExpectedConditions.visibilityOfElementLocated(By.xpath(waitUntilXPath))));

            String ActualResult = driver.findElement(By.xpath(actualXPath)).getText();
            String ExpectedResult = Expected;
            Assert.assertTrue(ActualResult.contains(ExpectedResult), "Fail: Actual result does not contain expected result.");
            System.out.println("Pass");
        } catch (Exception e) {
            System.out.println("Fail");
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        } finally {
            // Ensure that the WebDriver instance is closed regardless of whether an exception occurred

        }
    }
    public static void loginChecking(AppiumDriver driver, int seconds, FormLogin loginParameters, CheckingParamater checkingParamater){
        WebDriverWait wait = accessLoginPage(seconds,driver);
        wait.until((ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='login_logo']"))));
        String username = "standard_user";
        String password = "secret_sauce";
        fillLoginForm(driver,loginParameters.getUsernameXPath(),loginParameters.getUsername(),loginParameters.getPasswordXPath(),loginParameters.getPassword(), loginParameters.getButtonXPath());

        //login check
        checking(driver,wait,checkingParamater.getWaitUntilXPath(),checkingParamater.getActualXPath(),checkingParamater.getExpected());
    }
}
