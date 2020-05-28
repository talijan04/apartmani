package com.talijan04.testiranje.apartmani.dao;


import com.talijan04.testiranje.apartmani.model.Apartman;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository("postgres")
public class ApartmanDataAccessService implements IApartmanDao {

    private final JdbcTemplate jdbcTemplate;

    public ApartmanDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Apartman> selectAllApartmans() {
        //return List.of(new Apartman(1, "Vila Jugovo - from postgres", 1200.33));
        final String SQL_ALL_APARTMANI = "SELECT id, naziv, cena, slika FROM apartman ORDER BY id";
        /* return jdbcTemplate.query(SQL_ALL, (resultSet, i) -> {
            int id = resultSet.getInt("id");
            String naziv = resultSet.getString("naziv");
            double cena = resultSet.getDouble("cena");
            return new Apartman(id, naziv, cena);
        } );*/
        return jdbcTemplate.query(SQL_ALL_APARTMANI, new ApartmanRowMapper());
    }


    @Override
    public Optional<Apartman> selectApartmaneById(int id) {
        final String SQL_SELECT_APARTMAN_BY_ID = "SELECT id, naziv, cena, slika FROM apartman WHERE id = ?";
      /*   Apartman apartman =  jdbcTemplate.queryForObject(
                SQL_SELECT_APARTMAN_BY_ID,
                new Object[]{id},
                (resultSet, i) -> {
                    int apartmanId = resultSet.getInt("id");
                    String naziv = resultSet.getString("naziv");
                    double cena = resultSet.getDouble("cena");
                    return new Apartman(id, naziv, cena);
                });*/
      Apartman apartman = jdbcTemplate.queryForObject(SQL_SELECT_APARTMAN_BY_ID, new Object[]{id}, new ApartmanRowMapper());
    return Optional.ofNullable(apartman);
    }

    @Override
    public int insertApartman(Apartman apartman) {
        final String SQL_INSERT = "INSERT INTO apartman (id, naziv, cena) values (?, ?, ?)";
        jdbcTemplate.update(SQL_INSERT,apartman.getId(), apartman.getNaziv(), apartman.getCena());
        return 1;
    }

    @Override
    public int deleteApartmanById(int id) {
        final String SQL_DELETE = "DELETE FROM apartman WHERE id = ?";
        jdbcTemplate.update(SQL_DELETE, id);
        return 1;
    }

    @Override
    public int updateApartmanById(int id, Apartman apartman) {
        final String SQL_UPDATE = "UPDATE apartman SET naziv = ?, cena = ? WHERE id = ?";
        jdbcTemplate.update(SQL_UPDATE,apartman.getNaziv(), apartman.getCena(), id);
        return 1;
    }
}
