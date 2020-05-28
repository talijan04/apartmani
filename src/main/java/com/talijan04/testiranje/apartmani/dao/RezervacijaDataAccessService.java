package com.talijan04.testiranje.apartmani.dao;

import com.talijan04.testiranje.apartmani.model.Rezervacija;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("postgres3")
public class RezervacijaDataAccessService implements IRezervacijaDao {

    private final JdbcTemplate jdbcTemplate;

    public RezervacijaDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Rezervacija> selectAllReservations() {
        final String SQL_ALL_REZERVACIJE = "SELECT id, apartmanid, ime, prezime, brlk, email, datefrom, dateto FROM rezervacije ORDER BY id";
        return jdbcTemplate.query(SQL_ALL_REZERVACIJE, new RezervacijaRowMapper());
    }

    @Override
    public Optional<Rezervacija> selectRezervacijaById(Integer id) {
        return Optional.empty();
    }

    @Override
    public int insertRezervacija(Rezervacija rezervacija) {
        final String SQL_INSERT = "INSERT INTO rezervacije (apartmanID, ime, prezime, brlk, email, dateFrom, dateTo) values (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(SQL_INSERT, rezervacija.getApartmanId(), rezervacija.getIme(), rezervacija.getPrezime(), rezervacija.getBrLk(), rezervacija.getEmail(),
               rezervacija.getDateFrom(), rezervacija.getDateTo());
        return 1;
    }

    @Override
    public int deleteRezervacijaById(Integer id) {
        return 0;
    }

    @Override
    public int updateReyervacijaById(Integer id, Rezervacija rezervacija) {
        return 0;
    }
}
