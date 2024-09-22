package com.test.example;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class LoginTestIOS {
    AppiumDriver driver;
    FormLogin formLogin;
    CheckingParamater checkingParamaterValidData;
    CheckingParamater checkingParamaterNotValidData;

    @BeforeTest
     public void setup() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName","iOS");
        caps.setCapability("appium:platformVersion", "17.4");
        caps.setCapability("appium:deviceName", "iPhone SE (3rd generation)");
        caps.setCapability("appium:automationName", "XCUITest");
        caps.setCapability("browserName", "safari");
        caps.setCapability("Safari:useSimulator",true);
        driver = new IOSDriver(new URL("http://0.0.0.0:4723/"),caps);

        formLogin = new FormLogin.Builder()
                .setUsername("")
                .setUsernameXPath("//input[@id='user-name']")
                .setPassword("")
                .setPasswordXPath("//input[@id='password']")
                .setButtonXPath("//input[@id='login-button']")
                .build();

        checkingParamaterValidData = new CheckingParamater.Builder()
                .setWaitUntilXPath("//span[@class='title']")
                .setActualXPath("//span[@class='title']")
                .setExpected("Products")
                .build();

        checkingParamaterNotValidData = new CheckingParamater.Builder()
                .setWaitUntilXPath("//div[@class='error-message-container error']")
                .setActualXPath("//div[@class='error-message-container error']//h3")
                .setExpected("")
                .build();
    }

    @Test
    public void LG001(){
        WebDriverWait wait =tools.accessLoginPage(15,driver);
        tools.checking(driver,wait,"//div[@class='login_logo']","//div[@class='login_logo']","Swag Labs");
    }

    @Test
    public void LG002(){
        formLogin.setUsername("standard_user");
        formLogin.setPassword("secret_sauce");

        tools.loginChecking(driver,15,formLogin,checkingParamaterValidData);
    }

    @Test
    public void LG003(){
        formLogin.setUsername("standard_user");
        formLogin.setPassword("secret_123");

        checkingParamaterNotValidData.setExpected("Epic sadface: Username and password do not match any user in this service");

        tools.loginChecking(driver,15,formLogin,checkingParamaterNotValidData);
    }

    @Test
    public void LG004(){
        formLogin.setUsername("standard_user");
        formLogin.setPassword("");

        checkingParamaterNotValidData.setExpected("Epic sadface: Password is required");

        tools.loginChecking(driver,15,formLogin,checkingParamaterNotValidData);
    }

    @Test
    public void LG005(){
        formLogin.setUsername("' OR '1'='1");
        formLogin.setPassword("' OR '1'='1");

        checkingParamaterNotValidData.setExpected("Epic sadface: Username and password do not match any user in this service");

        tools.loginChecking(driver,15,formLogin,checkingParamaterNotValidData);
    }

    @Test
    public void LG006(){
        formLogin.setUsername("standard_user");
        formLogin.setPassword("secret_123");

        checkingParamaterNotValidData.setExpected("Epic sadface: Username and password do not match any user in this service");


        for (int i = 0; i < 3; i++) {
            System.out.println("Attempt : "+(i+1));
            tools.loginChecking(driver,5,formLogin,checkingParamaterNotValidData);
        }

        formLogin.setUsername("standard_user");
        formLogin.setPassword("secret_sauce");

        checkingParamaterNotValidData.setExpected("suspend");

        tools.loginChecking(driver,5,formLogin, checkingParamaterNotValidData);
    }
    @AfterTest
    public void close(){
        if (driver != null) {
            driver.quit(); // Closes all browser windows and ends the WebDriver session
        }
    }

}

