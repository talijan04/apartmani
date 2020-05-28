package com.talijan04.testiranje.apartmani.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PageAccessTest {
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
    public void tryToOpenSecuredLinkNew_ShouldReturnLoginPage(){
        browser.get("http://localhost:8080/new");
        String expectedUrl="http://localhost:8080/login";
        String actualUrl= browser.getCurrentUrl();
        Assertions.assertEquals(expectedUrl,actualUrl);
    }

    @Test
    public void tryToOpenSecuredLinkUpdate_ShouldReturnLoginPage(){
        browser.get("http://localhost:8080/update");
        String expectedUrl="http://localhost:8080/login";
        String actualUrl= browser.getCurrentUrl();
        Assertions.assertEquals(expectedUrl,actualUrl);
    }

    @Test
    public void tryToOpenSecuredLinkList_ShouldReturnLoginPage(){
        browser.get("http://localhost:8080/list");
        String expectedUrl="http://localhost:8080/login";
        String actualUrl= browser.getCurrentUrl();
        Assertions.assertEquals(expectedUrl,actualUrl);
    }

    @Test
    public void tryToOpenSecuredLinkSave_ShouldReturnLoginPage(){
        browser.get("http://localhost:8080/save");
        String expectedUrl="http://localhost:8080/login";
        String actualUrl= browser.getCurrentUrl();
        Assertions.assertEquals(expectedUrl,actualUrl);
    }

    @Test
    public void tryToOpenSecuredLinkEdit_ShouldReturnLoginPage(){
        browser.get("http://localhost:8080/edit");
        String expectedUrl="http://localhost:8080/login";
        String actualUrl= browser.getCurrentUrl();
        Assertions.assertEquals(expectedUrl,actualUrl);
    }

    @Test
    public void tryToOpenSecuredLinkEditApartman_ShouldReturnLoginPage(){
        browser.get("http://localhost:8080/edit/*");
        String expectedUrl="http://localhost:8080/login";
        String actualUrl= browser.getCurrentUrl();
        Assertions.assertEquals(expectedUrl,actualUrl);
    }

    @Test
    public void tryToOpenSecuredLinkAdmincenter_ShouldReturnLoginPage(){
        browser.get("http://localhost:8080/admincenter");
        String expectedUrl="http://localhost:8080/login";
        String actualUrl= browser.getCurrentUrl();
        Assertions.assertEquals(expectedUrl,actualUrl);
    }

    @Test
    public void tryToOpenSecuredLinkAdmincenterDelete_ShouldReturnLoginPage(){
        browser.get("http://localhost:8080/admincenter-delete");
        String expectedUrl="http://localhost:8080/login";
        String actualUrl= browser.getCurrentUrl();
        Assertions.assertEquals(expectedUrl,actualUrl);
    }

    @Test
    public void tryToOpenSecuredLinkRezervacije_ShouldReturnLoginPage(){
        browser.get("http://localhost:8080/rezervacije");
        String expectedUrl="http://localhost:8080/login";
        String actualUrl= browser.getCurrentUrl();
        Assertions.assertEquals(expectedUrl,actualUrl);
    }

    @Test
    public void tryToOpenSecuredLinkDelete_ShouldReturnLoginPage(){
        browser.get("http://localhost:8080/delete");
        String expectedUrl="http://localhost:8080/login";
        String actualUrl= browser.getCurrentUrl();
        Assertions.assertEquals(expectedUrl,actualUrl);
    }

    @Test
    public void tryToOpenSecuredLinkUser_ShouldReturnLoginPage(){
        browser.get("http://localhost:8080/user");
        String expectedUrl="http://localhost:8080/login";
        String actualUrl= browser.getCurrentUrl();
        Assertions.assertEquals(expectedUrl,actualUrl);
    }

    @Test
    public void tryToOpenSecuredLinkHome_ShouldReturnLoginPage(){
        browser.get("http://localhost:8080");
        String expectedUrl="http://localhost:8080/";
        String actualUrl= browser.getCurrentUrl();
        Assertions.assertEquals(expectedUrl,actualUrl);
    }
}
