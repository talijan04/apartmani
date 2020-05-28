package com.talijan04.testiranje.apartmani.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApartmanTest {

    @Test
    void getSlika() {
        Apartman apartman = new Apartman( 1, "Apartman", 5000,"/img/slika1.jpg" );
        Assertions.assertEquals("/img/slika1.jpg", apartman.getSlika());
    }

    @Test
    void setSlika() {
        Apartman apartman = new Apartman();
        apartman.setSlika("/img/slika1.jpg");
        Assertions.assertEquals("/img/slika1.jpg", apartman.getSlika());
    }

    @Test
    void setId() {
        Apartman apartman = new Apartman();
        apartman.setId(1);
        Assertions.assertEquals(1, apartman.getId());
    }

    @Test
    void setNaziv() {
        Apartman apartman = new Apartman();
        apartman.setNaziv("Apartman");
        Assertions.assertEquals("Apartman", apartman.getNaziv());
    }

    @Test
    void setCena() {
        Apartman apartman = new Apartman();
        apartman.setCena(5000.00);
        Assertions.assertEquals(5000.00, apartman.getCena());
    }

    @Test
    void getId() {
        Apartman apartman = new Apartman( 1, "Apartman", 5000,"/img/slika1.jpg" );
        Assertions.assertEquals(1, apartman.getId());
    }

    @Test
    void getNaziv() {
        Apartman apartman = new Apartman( 1, "Apartman", 5000,"/img/slika1.jpg" );
        Assertions.assertEquals("Apartman", apartman.getNaziv());
    }

    @Test
    void getCena() {
        Apartman apartman = new Apartman( 1, "Apartman", 5000,"/img/slika1.jpg" );
        Assertions.assertEquals(5000, apartman.getCena());
    }
}