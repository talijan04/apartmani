package com.talijan04.testiranje.apartmani.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class RezervacijaTest {

    @Test
    void getId() {
        Rezervacija rezervacija = new Rezervacija(1,1,"Aleksandar","Jovanović","00254781", "talijan04@gmail.com", LocalDate.parse("19/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("20/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        int expected = 1;
        int actual = rezervacija.getId();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getApartmanId() {
        Rezervacija rezervacija = new Rezervacija(1,1,"Aleksandar","Jovanović","00254781", "talijan04@gmail.com", LocalDate.parse("19/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("20/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        int expected = 1;
        int actual = rezervacija.getApartmanId();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getIme() {
        Rezervacija rezervacija = new Rezervacija(1,1,"Aleksandar","Jovanović","00254781", "talijan04@gmail.com", LocalDate.parse("19/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("20/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        String expected = "Aleksandar";
        String actual = rezervacija.getIme();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getPrezime() {
        Rezervacija rezervacija = new Rezervacija(1,1,"Aleksandar","Jovanović","00254781", "talijan04@gmail.com", LocalDate.parse("19/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("20/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        String expected = "Jovanović";
        String actual = rezervacija.getPrezime();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getBrLk() {
        Rezervacija rezervacija = new Rezervacija(1,1,"Aleksandar","Jovanović","00254781", "talijan04@gmail.com", LocalDate.parse("19/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("20/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        String expected = "00254781";
        String actual = rezervacija.getBrLk();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getEmail() {
        Rezervacija rezervacija = new Rezervacija(1,1,"Aleksandar","Jovanović","00254781", "talijan04@gmail.com", LocalDate.parse("19/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("20/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        String expected = "talijan04@gmail.com";
        String actual = rezervacija.getEmail();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getDateFrom() {
        Rezervacija rezervacija = new Rezervacija(1,1,"Aleksandar","Jovanović","00254781", "talijan04@gmail.com", LocalDate.parse("19/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("20/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        LocalDate expected = LocalDate.parse("19/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate actual = rezervacija.getDateFrom();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getDateTo() {
        Rezervacija rezervacija = new Rezervacija(1,1,"Aleksandar","Jovanović","00254781", "talijan04@gmail.com", LocalDate.parse("19/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("20/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        LocalDate expected = LocalDate.parse("20/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate actual = rezervacija.getDateTo();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void isStatus() {
        Rezervacija rezervacija = new Rezervacija();
        Assertions.assertEquals(true, rezervacija.isStatus());
    }

    @Test
    void setId() {
        Rezervacija rezervacija = new Rezervacija();
        rezervacija.setId(1);
        Assertions.assertEquals(1, rezervacija.getId());
    }

    @Test
    void setApartmanId() {
        Rezervacija rezervacija = new Rezervacija();
        rezervacija.setApartmanId(1);
        Assertions.assertEquals(1, rezervacija.getApartmanId());
    }

    @Test
    void setIme() {
        Rezervacija rezervacija = new Rezervacija();
        rezervacija.setIme("Aleksandar");
        Assertions.assertEquals("Aleksandar", rezervacija.getIme());
    }

    @Test
    void setPrezime() {
        Rezervacija rezervacija = new Rezervacija();
        rezervacija.setPrezime("Jovanović");
        Assertions.assertEquals("Jovanović", rezervacija.getPrezime());
    }

    @Test
    void setBrLk() {
        Rezervacija rezervacija = new Rezervacija();
        rezervacija.setBrLk("00254781");
        Assertions.assertEquals("00254781", rezervacija.getBrLk());
    }

    @Test
    void setEmail() {
        Rezervacija rezervacija = new Rezervacija();
        rezervacija.setEmail("talijan04@gmail.com");
        Assertions.assertEquals("talijan04@gmail.com", rezervacija.getEmail());
    }

    @Test
    void setStatus() {
        Rezervacija rezervacija = new Rezervacija();
        rezervacija.setStatus(true);
        Assertions.assertEquals(true, rezervacija.isStatus());
    }

    @Test
    void setDateFrom() {
        Rezervacija rezervacija = new Rezervacija();
        rezervacija.setDateFrom(LocalDate.parse("19/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        LocalDate expected = LocalDate.parse("19/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate actual = rezervacija.getDateFrom();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void setDateTo() {
        Rezervacija rezervacija = new Rezervacija();
        rezervacija.setDateTo(LocalDate.parse("19/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        LocalDate expected = LocalDate.parse("19/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate actual = rezervacija.getDateTo();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getUkupanBrojNocenja_ShouldReturn1Day_IfEnter() {
        Rezervacija rezervacija = new Rezervacija(1,1,"Aleksandar","Jovanović","00254781", "talijan04@gmail.com", LocalDate.parse("19/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("20/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        int expected = 1;
        int actual = rezervacija.getUkupanBrojNocenja();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getUkupanBrojNocenja_ShouldReturn1Day_IfEnterBigDifferendeBetweenTwoDays() {
        Rezervacija rezervacija = new Rezervacija(1,1,"Aleksandar","Jovanović","00254781", "talijan04@gmail.com", LocalDate.parse("19/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("20/06/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        int expected = 32;
        int actual = rezervacija.getUkupanBrojNocenja();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getUkupanBrojNocenja_shouldReturnZero_IfUserEnterSameDates() {
        Rezervacija rezervacija = new Rezervacija(1,1,"Aleksandar","Jovanović","00254781", "talijan04@gmail.com", LocalDate.parse("19/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("19/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        int expected = 0;
        int actual = rezervacija.getUkupanBrojNocenja();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getUkupanBrojNocenja_shouldReturnZero_IfUserEnterWrongDates() {
        Rezervacija rezervacija = new Rezervacija(1,1,"Aleksandar","Jovanović","00254781", "talijan04@gmail.com", LocalDate.parse("20/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("19/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        int expected = 0;
        int actual = rezervacija.getUkupanBrojNocenja();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getUkupanBrojNocenjaPogresno_ShouldReturn1Day_IfEnterBigDifferendeBetweenTwoDays() {
        Rezervacija rezervacija = new Rezervacija(1,1,"Aleksandar","Jovanović","00254781", "talijan04@gmail.com", LocalDate.parse("19/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("20/06/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        int expected = 32;
        int actual = rezervacija.getUkupanBrojNocenjaPogresno();
        Assertions.assertEquals(expected, actual);
    }
}