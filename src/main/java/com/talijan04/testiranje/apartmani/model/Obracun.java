package com.talijan04.testiranje.apartmani.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.text.DecimalFormat;

@Entity
public class Obracun {

    @Id
    private Integer id;
    private int ukupanBrojNocenja;
    @Column(precision=10, scale=2)
    private double cena;
    @Column(precision=10, scale=2)
    private double ukupnaCenaZaNocenja;
    @Column(precision=10, scale=2)
    private double pdv;
    @Column(precision=10, scale=2)
    private double osnovica;
    @Column(precision=10, scale=2)
    private double boravisnaTaksa;
    @Column(precision=10, scale=2)
    private double ukupnaCenaBoravka;


    public Obracun() {
      }

    public Obracun(int id, int ukupanBrojNocenja, double cena, double boravisnaTaksa) {
        this.id = id;
        this.ukupanBrojNocenja = ukupanBrojNocenja;
        this.cena = cena;
        this.boravisnaTaksa = (double) Math.round(this.ukupanBrojNocenja * this.boravisnaTaksa * 100.0)/100.0;
        System.out.println(this.boravisnaTaksa);
        this.ukupnaCenaZaNocenja = Math.round(this.ukupanBrojNocenja * this.cena * 100)/100;
        System.out.println(this.ukupnaCenaZaNocenja);
        this.osnovica = Math.round(ukupnaCenaZaNocenja / 1.2 * 100)/100;
        System.out.println(this.osnovica);
        this.pdv = ukupnaCenaZaNocenja-osnovica;
        System.out.println(this.pdv);
        this.ukupnaCenaBoravka = this.ukupnaCenaZaNocenja+this.boravisnaTaksa;
        System.out.println(this.ukupnaCenaBoravka);
    }



    public Integer getId() {
        return id;
    }

    public int getUkupanBrojNocenja() {
        return ukupanBrojNocenja;
    }

    public double getCena() {
        return cena;
    }

    public double getUkupnaCenaZaNocenja() {
        return ukupnaCenaZaNocenja;
    }

    public double getPdv() {
        return pdv;
    }

    public double getOsnovica() {
        return osnovica;
    }

    public double getBoravisnaTaksa() {
        return boravisnaTaksa;
    }

    public double getUkupnaCenaBoravka() {
        return ukupnaCenaBoravka;
    }

    public Double getUkupnaCenaBoravkaFormatted() {
        DecimalFormat df2 = new DecimalFormat(".##");
        return Double.valueOf(df2.format(this.ukupnaCenaBoravka));
    }
}
