package com.talijan04.testiranje.apartmani.dao;

import com.talijan04.testiranje.apartmani.model.Apartman;
import com.talijan04.testiranje.apartmani.model.Rezervacija;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RezervacijaRowMapper implements RowMapper<Rezervacija> {

    @Override
    public Rezervacija mapRow(ResultSet resultSet, int i) throws SQLException {
        Rezervacija rezervacija = new Rezervacija();
        rezervacija.setId(resultSet.getInt("id"));
        rezervacija.setApartmanId(resultSet.getInt("apartmanid"));
        rezervacija.setIme(resultSet.getString("ime"));
        rezervacija.setPrezime(resultSet.getString("prezime"));
        rezervacija.setBrLk(resultSet.getString("brlk"));
        rezervacija.setEmail(resultSet.getString("email"));
       // rezervacija.setDateFrom(resultSet.getDate("datefrom"));
       // rezervacija.setDateFrom(resultSet.getDate("dateto"));
     return rezervacija;
    }
}
