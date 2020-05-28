package com.talijan04.testiranje.apartmani.model;

import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

@Entity
public class Rezervacija {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull(message = "{apartmanId.notnull}")
    private Integer apartmanId;
    @NotNull(message = "{ime.notnull}")
    @Size(min = 2, max = 30, message = "{ime.size}")
    private String ime;
    private String srednjeIme;
    @NotNull(message = "{prezime.notnull}")
    @Size(min = 2, max = 30, message = "{prezime.size}")
    private String prezime;
    @Pattern(regexp = "^[0-9]{1,8}$", message = "{brLk.format}")
    private String brLk;
    @Email(message = "{email.format}")
    //@Column(unique = true, nullable = false)
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "{dateFrom.notnull}")
    private LocalDate dateFrom;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "{dateTo.notnull}")
    private LocalDate dateTo;
    private boolean status;

    public Rezervacija() {
        this.status = true;
    }

    public Rezervacija(Integer id, Integer apartmanId, String ime, String prezime, String brLk, String email, LocalDate dateFrom, LocalDate dateTo) {
        this.id = id;
        this.apartmanId = apartmanId;
        this.ime = ime;
        this.prezime = prezime;
        this.brLk = brLk;
        this.email = email;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.status = true;
    }

    public Integer getId() {
        return id;
    }

    public Integer getApartmanId() {
        return apartmanId;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getBrLk() {
        return brLk;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setApartmanId(Integer apartmanId) {
        this.apartmanId = apartmanId;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setSrednjeIme(String srednjeIme) {
        this.srednjeIme = srednjeIme;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setBrLk(String brLk) {
        this.brLk = brLk;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public int getUkupanBrojNocenja() {
        if (dateFrom.isBefore(dateTo)) {
            return (int) ChronoUnit.DAYS.between(dateFrom, dateTo);
        } else {
            //throw
            return 0;
        }
    }

    public int getUkupanBrojNocenjaPogresno() {
        Period period = Period.between(dateFrom, dateTo);
        return period.getDays();
    }
}
