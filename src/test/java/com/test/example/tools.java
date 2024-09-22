package com.test.example;

import com.test.example.CheckingParamater;
import com.test.example.FormLogin;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class tools {

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
