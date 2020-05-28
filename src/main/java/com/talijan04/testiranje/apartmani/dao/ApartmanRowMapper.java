package com.talijan04.testiranje.apartmani.dao;

import com.talijan04.testiranje.apartmani.model.Apartman;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ApartmanRowMapper implements RowMapper<Apartman> {
    @Override
    public Apartman mapRow(ResultSet resultSet, int i) throws SQLException {
        Apartman apartman = new Apartman();
             apartman.setId(resultSet.getInt("id"));
             apartman.setNaziv(resultSet.getString("naziv"));
             apartman.setCena(resultSet.getDouble("cena"));
             apartman.setSlika(resultSet.getString("slika"));
        return apartman;
    }
}
