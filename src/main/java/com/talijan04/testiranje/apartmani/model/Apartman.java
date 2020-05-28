package com.talijan04.testiranje.apartmani.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;

public class Apartman {

    private int id;
    private String naziv;
    private double cena;
    private String slika;

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Apartman(@JsonProperty("id") int id,
                    @JsonProperty("naziv") String naziv,
                    @JsonProperty("cena") double cena,
                    String slika) {
        this.id = id;
        this.naziv = naziv;
        this.cena = cena;
        this.slika = slika;
    }

    public Apartman() {

    }

    public int getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public double getCena() {
        return cena;
    }
}
