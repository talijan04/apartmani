package com.talijan04.testiranje.apartmani.service;

import com.talijan04.testiranje.apartmani.dao.IRezervacijaDao;
import com.talijan04.testiranje.apartmani.model.Apartman;
import com.talijan04.testiranje.apartmani.model.Rezervacija;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RezervacijaService {

    private final IRezervacijaDao iRezervacijaDao;

    @Autowired
    public RezervacijaService(@Qualifier("postgres3") IRezervacijaDao iRezervacijaDao) {
        this.iRezervacijaDao = iRezervacijaDao;
    }

    public List<Rezervacija> getAllRezervations(){
        return iRezervacijaDao.selectAllReservations();
    }

    public int addRezervacija(Rezervacija rezervacija){
        return iRezervacijaDao.insertRezervacija(rezervacija);
    }


}
