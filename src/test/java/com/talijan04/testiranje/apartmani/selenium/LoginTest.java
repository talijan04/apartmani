package com.talijan04.testiranje.apartmani.selenium;

import org.apache.tomcat.jni.Global;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
    private WebDriver browser;

    @BeforeEach
    public void createWebBrowserInstance(){
        System.setProperty(GlobalData.driverType,GlobalData.driverLocation);
        browser = new ChromeDriver();
    }

    @AfterEach
    public void destroyTheWebBrowser(){
        browser.close();
        browser.quit();
    }

    @Test
    public void loginButtonClick_ShouldOpenErrorPage_WhenEnteredIncorrectCredentials(){

        browser.get("http://localhost:8080/login");
        try {Thread.sleep(2000);} catch(Exception e) {}

        browser.findElement(By.id("username")).sendKeys("netacno");
        browser.findElement(By.id("password")).sendKeys("netacno");
        browser.findElement(By.xpath("//button[text()='Sign in']")).click();

        String actualUrl="http://localhost:8080/login?error";
        String expectedUrl= browser.getCurrentUrl();
        Assertions.assertEquals(expectedUrl,actualUrl);
    }

    @Test
    public void loginButtonClick_ShouldOpenErrorPageAgain_WhenUsernameAndPasswordDosentEntered(){

        browser.get("http://localhost:8080/login");
        try {Thread.sleep(2000);} catch(Exception e) {}

        browser.findElement(By.xpath("//button[text()='Sign in']")).click();

        String actualUrl="http://localhost:8080/login";
        String expectedUrl= browser.getCurrentUrl();
        Assertions.assertEquals(expectedUrl,actualUrl);
    }

    @Test
    public void loginButtonClick_ShouldOpenListPage(){

        browser.get(GlobalData.baseUrl);

        browser.findElement(By.id("btnLogin")).click();
        try {Thread.sleep(2000);} catch(Exception e) {}

        browser.findElement(By.id("username")).sendKeys(GlobalData.username);
        browser.findElement(By.id("password")).sendKeys(GlobalData.password);

        browser.findElement(By.xpath("//button[text()='Sign in']")).click();

        String expectedUrl="http://localhost:8080/list";
        String actualUrl= browser.getCurrentUrl();
        Assertions.assertEquals(expectedUrl,actualUrl);
    }

}
