package com.talijan04.testiranje.apartmani.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;

public class RezervacijaExampleTest {
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
    public void addNewRegistration_ShouldOpenConfirmationPage_WhenReservationAddedFirstTime() throws ParseException {

        //Korisnik bez logovanja ostavlja rezervaciju
        System.out.println(" - Korisnik bez logovanja ostavlja rezervaciju");
        //browser.manage().window().maximize();
        browser.get(GlobalData.baseUrl);
        browser.findElement(By.xpath("//button[text()='REZERVIŠI']")).click();

        Select drpCountry = new Select(browser.findElement(By.name("apartmanId")));
        drpCountry.selectByVisibleText("Apartman Pogled");

        browser.findElement(By.id("ime")).sendKeys(GlobalData.ime);
        browser.findElement(By.id("prezime")).sendKeys(GlobalData.prezime);
        browser.findElement(By.id("brLk")).sendKeys(GlobalData.brLk);
        browser.findElement(By.id("email")).sendKeys(GlobalData.email);
        browser.findElement(By.id("dateFrom")).sendKeys(GlobalData.dateFrom);
        browser.findElement(By.id("dateTo")).sendKeys(GlobalData.dateTo);
        browser.findElement(By.xpath("//button[text()='Rezerviši']")).click();
        //try {Thread.sleep(10000);} catch(Exception e) {}

        String expectedUrl = "http://localhost:8080/results";
        String actualUrl = browser.getCurrentUrl();
        Assertions.assertEquals(expectedUrl, actualUrl);

        //Logovanje na aplikaciju
        System.out.println(" - Loguje se na aplikaciju");
        browser.findElement(By.id("btnLogin")).click();
        try { Thread.sleep(2000); } catch (Exception e) {   }
        browser.findElement(By.id("username")).sendKeys(GlobalData.username);
        browser.findElement(By.id("password")).sendKeys(GlobalData.password);
        browser.findElement(By.xpath("//button[text()='Sign in']")).click();
        try { Thread.sleep(2000); } catch (Exception e) { }
        String expectedUrl2 = "http://localhost:8080/list";
        String actualUrl2 = browser.getCurrentUrl();
        Assertions.assertEquals(expectedUrl2, actualUrl2);

        //Otvara stranicu sa rezervacijama
        System.out.println(" - Otvara stranicu sa rezervacijama");
        WebElement rezervacije = browser.findElement(By.xpath("//html/body/div/nav/div/ul/li[4]/a"));
        //WebElement rezervacije = browser.findElement(By.linkText("Rezervacije"));
        rezervacije.click();
        String expectedUrl3 = "http://localhost:8080/rezervacije";
        String actualUrl3 = browser.getCurrentUrl();
        Assertions.assertEquals(expectedUrl3, actualUrl3);
        //try {Thread.sleep(10000);} catch(Exception e) {}

        //Pronalazi poslednju rezervaciju
        System.out.println(" - Pronalazi poslednju rezervaciju");
        String innerText = browser.findElement(By.xpath("//table/thead/tr/th[1]")).getText();
        Assertions.assertEquals("Id", innerText);

        List<WebElement> rows = browser.findElements(By.xpath("//table/tbody/tr/td[1]"));
        long brojRedova = rows.size();
        System.out.println("      Broj redova : " + brojRedova);

        String max;
        int r = 0, m = 0;
        long j = 0;

        for (int i = 0; i < brojRedova; i++) {
            max = browser.findElement(By.xpath("//table/tbody/tr[" + (i + 1) + "]/td[1]")).getText();
            System.out.println("      " + (i + 1) + " - " + max);
            NumberFormat f = NumberFormat.getNumberInstance();
            Number num = f.parse(max);
            max = num.toString();
            m = (int) Double.parseDouble(max);

            if (m > r) {
                r = m;
                j = i + 1;
            }
        }

        System.out.println("      Poslednji zapis je pod Id brojem: " + r);
        Assertions.assertEquals(String.valueOf(r), browser.findElement(By.xpath("//table/tbody/tr[" + (j) + "]/td[1]")).getText());
        Assertions.assertEquals(String.valueOf(1), browser.findElement(By.xpath("//table/tbody/tr[" + (j) + "]/td[2]")).getText());
        Assertions.assertEquals(GlobalData.ime, browser.findElement(By.xpath("//table/tbody/tr[" + (j) + "]/td[3]")).getText());
        Assertions.assertEquals(GlobalData.prezime, browser.findElement(By.xpath("//table/tbody/tr[" + (j) + "]/td[4]")).getText());
        Assertions.assertEquals(GlobalData.brLk, browser.findElement(By.xpath("//table/tbody/tr[" + (j) + "]/td[5]")).getText());
        Assertions.assertEquals(GlobalData.email, browser.findElement(By.xpath("//table/tbody/tr[" + (j) + "]/td[6]")).getText());
        Assertions.assertEquals(GlobalData.dateFrom, browser.findElement(By.xpath("//table/tbody/tr[" + (j) + "]/td[7]")).getText());
        Assertions.assertEquals(GlobalData.dateTo, browser.findElement(By.xpath("//table/tbody/tr[" + (j) + "]/td[8]")).getText());

        //Pasiviziramo rezervaciju

        System.out.println(" - Pasiviziramo rezervaciju");
        System.out.println("      " + browser.findElement(By.xpath("//table/tbody/tr[" + (j) + "]/td[9]")).getText());
        //browser.findElement(By.xpath("//table/tbody/tr[" + (j)+ "]/td[9]/a/button")).click();
        browser.findElement(By.xpath("//*[@id=\"myTable\"]/tr[" + (j) + "]/td[9]/a/button")).click();

        //Proveravamo ponovo da li postoji rezervacija
        System.out.println(" - Proveravamo ponovo da li postoji rezervacija");
        List<WebElement> rows2 = browser.findElements(By.xpath("//table/tbody/tr/td[1]"));
        Assertions.assertEquals(brojRedova - 1, rows2.size());

        /*
        browser.findElement(By.id("myInput")).sendKeys(String.valueOf(r));
        List<WebElement> rows3 = browser.findElements(By.xpath("//table/tbody/tr/td[1]"));
        try {Thread.sleep(10000);} catch(Exception e) {}
        Assertions.assertEquals( 0 , rows3.size());
*/
        System.out.println(" - Kraj testa!");
    }

    @Test
    public void validationTest_ShouldShowValidationMessages_WhenLeaveAllFieldsEmpty() {
        browser.get(GlobalData.baseUrl);
        browser.findElement(By.xpath("//button[text()='REZERVIŠI']")).click();
        try {Thread.sleep(1000);} catch(Exception e) {}

        browser.findElement(By.id("email")).sendKeys("talijan04");
        browser.findElement(By.id("dateFrom")).clear();
        browser.findElement(By.id("dateTo")).clear();

        browser.findElement(By.xpath("//button[text()='Rezerviši']")).click();

        Assertions.assertEquals("Popunite ispravno sva polja.",browser.findElement(By.xpath("//table/tbody/tr[1]/td/div")).getText());
        Assertions.assertEquals("Ovaj termin je popunjen.",browser.findElement(By.xpath("//table/tbody/tr[2]/td/div")).getText());
        Assertions.assertEquals("Obavezno polje.",browser.findElement(By.xpath("//table/tbody/tr[3]/td[3]")).getText());
        Assertions.assertEquals("Unesite ime od 2 do 30 karaktera.",browser.findElement(By.xpath("//table/tbody/tr[4]/td[3]")).getText());
        Assertions.assertEquals("Unesite prezime od 2 do 30 karaktera.",browser.findElement(By.xpath("//table/tbody/tr[5]/td[3]")).getText());
        Assertions.assertEquals("Unesite osmocifreni broj.",browser.findElement(By.xpath("//table/tbody/tr[6]/td[3]")).getText());
        Assertions.assertEquals("Neispravan email.",browser.findElement(By.xpath("//table/tbody/tr[7]/td[3]")).getText());
        Assertions.assertEquals("Obavezno polje.",browser.findElement(By.xpath("//table/tbody/tr[8]/td[3]")).getText());
        Assertions.assertEquals("Obavezno polje.",browser.findElement(By.xpath("//table/tbody/tr[9]/td[3]")).getText());
    }

}
