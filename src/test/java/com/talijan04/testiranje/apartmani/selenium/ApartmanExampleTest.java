package com.talijan04.testiranje.apartmani.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.ParseException;
import java.util.List;

public class ApartmanExampleTest {

    private WebDriver browser;

    @BeforeEach
    public void createWebBrowserInstance() {
        System.setProperty(GlobalData.driverType, GlobalData.driverLocation);
        browser = new ChromeDriver();
    }

    @AfterEach
    public void destroyTheWebBrowser() {
        browser.close();
        browser.quit();
    }

    @Test
    public void workWithApartmans() {

        //Pocetak testa
        System.out.println(" * Pocetak testa *");

        //Logovanje na aplikaciju
        browser.get(GlobalData.listUrl);
        browser.findElement(By.id("username")).sendKeys(GlobalData.username);
        browser.findElement(By.id("password")).sendKeys(GlobalData.password);
        browser.findElement(By.xpath("//button[text()='Sign in']")).click();
        try { Thread.sleep(2000); } catch (Exception e) { }
        String expectedUrl = GlobalData.listUrl;
        String actualUrl = browser.getCurrentUrl();
        System.out.println(" - Loguje se na aplikaciju");
        Assertions.assertEquals(expectedUrl, actualUrl);

        //Proverava koliko ima apartmana
        List<WebElement> rows = browser.findElements(By.xpath("//table/tbody/tr/td[1]"));
        long brojApartmanaPreDodavanja = rows.size();
        System.out.println(" - Proverava koliko ima apartmana");
        System.out.println("      Broj apartmana : " + brojApartmanaPreDodavanja);

        //Dodavanja apartmana
        System.out.println(" - Dodavanje apartmana");
        browser.findElement(By.xpath("//button[text()='Dodaj Novi Apartman']")).click();
        browser.findElement(By.id("Id")).clear();
        browser.findElement(By.id("Id")).sendKeys("100");
        browser.findElement(By.id("naziv")).clear();
        browser.findElement(By.id("naziv")).sendKeys("Apartman za test");
        browser.findElement(By.id("cena")).clear();
        browser.findElement(By.id("cena")).sendKeys("2500.0");
        browser.findElement(By.xpath("//button[text()='Snimi']")).click();
        try {Thread.sleep(2000);} catch(Exception e) {}

        //Proverava da li je apartman dodat na listu
        List<WebElement> rows2 = browser.findElements(By.xpath("//table/tbody/tr/td[1]"));
        long brojApartmanaPosleDodavanja = rows2.size();
        System.out.println(" - Proverava koliko ima apartmana");
        System.out.println("      Novi broj apartmana : " + brojApartmanaPosleDodavanja);
        long expectedBrojApartmana = brojApartmanaPreDodavanja + 1;
        long actualBrojApartmana = brojApartmanaPosleDodavanja;
        Assertions.assertEquals(expectedBrojApartmana, actualBrojApartmana);

        WebElement tableRow = browser.findElement(By.xpath("//table/tbody/tr[" + brojApartmanaPosleDodavanja + "]"));
        System.out.println("      >>"+tableRow.getText()+"<<");
        String expectedAddedApartman = "100 Apartman za test 2500.0";
        String actualAddedApartman = tableRow.getText().substring(0, expectedAddedApartman.length());
        Assertions.assertEquals(expectedAddedApartman, actualAddedApartman);

        //Editovanje apartmana
        System.out.println(" - Editovanje apartmana");
        browser.findElement(By.xpath("//table/tbody/tr[" + brojApartmanaPosleDodavanja  + "]/td[4]/a[1]/button")).click();
        String expectedUrl2 = "http://localhost:8080/edit/100";
        String actualUrl2 = browser.getCurrentUrl();
        Assertions.assertEquals(expectedUrl2, actualUrl2);

        browser.findElement(By.id("Naziv")).clear();
        browser.findElement(By.id("Naziv")).sendKeys("Test apartman");
        browser.findElement(By.id("Cena")).clear();
        browser.findElement(By.id("Cena")).sendKeys("3000.0");
        browser.findElement(By.xpath("//button[text()='Snimi']")).click();

        //Proverava da li je promenjen apartman
        System.out.println(" - Proverava da li je promenjen apartman");
        String expectedUrl3 = GlobalData.listUrl;
        String actualUrl3 = browser.getCurrentUrl();
        Assertions.assertEquals(expectedUrl3, actualUrl3);

        WebElement tableRow2 = browser.findElement(By.xpath("//table/tbody/tr[" + brojApartmanaPosleDodavanja + "]"));
        System.out.println("      >>"+tableRow2.getText()+"<<");
        String expectedEditedApartman = "100 Test apartman 3000.0";
        String actualEditedApartman = tableRow2.getText().substring(0, expectedEditedApartman.length());
        Assertions.assertEquals(expectedEditedApartman, actualEditedApartman);


        //Briše apartman
        System.out.println(" - Brisanje apartmana");
        browser.findElement(By.xpath("//table/tbody/tr[" + brojApartmanaPosleDodavanja  + "]/td[4]/a[2]/button")).click();

        //Proverava da li je obrisan apartman
        System.out.println(" - Proverava da li je obrisan apartman");
        List<WebElement> rows3 = browser.findElements(By.xpath("//table/tbody/tr/td[1]"));
        long expectedBrojApartmana2 = brojApartmanaPosleDodavanja - 1;
        long actualBrojApartmanaPosleBrisanja = rows3.size();;
        Assertions.assertEquals(expectedBrojApartmana2, actualBrojApartmanaPosleBrisanja);

        //Kraj testa
        System.out.println(" * Kraj testa *");
    }

}
