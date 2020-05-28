package com.talijan04.testiranje.apartmani.dao;

import com.talijan04.testiranje.apartmani.model.Apartman;
import com.talijan04.testiranje.apartmani.model.Rezervacija;

import java.util.List;
import java.util.Optional;

public interface IRezervacijaDao {

    List<Rezervacija> selectAllReservations();

    Optional<Rezervacija> selectRezervacijaById(Integer id);

    int insertRezervacija(Rezervacija rezervacija);

    int deleteRezervacijaById(Integer id);

    int updateReyervacijaById(Integer id, Rezervacija rezervacija);

}
