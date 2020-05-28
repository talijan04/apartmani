package com.talijan04.testiranje.apartmani.selenium;

public class GlobalData {

    // parametri web drajvera
    static final String driverType = "webdriver.chrome.driver";
    static final String driverLocation = "chromedriver.exe";


    // parametri za logovanje u aplikaciju
    static final String baseUrl = "http://localhost:8080";
    static final String loginUrl = baseUrl + "/login";
    static final String listUrl = baseUrl + "/list";
    static final String username = "admin";
    static final String password = "admin";

    // podaci za test primer rezervacije
     static final String ime = "Aleksandar";
     static final String prezime = "Jovanovic";
     static final String brLk = "00254781";
     static final String email = "talijan04@gmail.com";
     static final String dateFrom = "22/05/2020";
     static final String dateTo = "23/05/2020";

}
