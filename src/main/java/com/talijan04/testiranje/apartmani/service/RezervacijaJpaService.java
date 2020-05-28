package com.talijan04.testiranje.apartmani.service;

import com.fasterxml.jackson.datatype.jsr310.deser.key.LocalDateKeyDeserializer;
import com.talijan04.testiranje.apartmani.dao.IRezervacijaDao;
import com.talijan04.testiranje.apartmani.dao.IRezervacijaJpaDao;
import com.talijan04.testiranje.apartmani.model.Rezervacija;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RezervacijaJpaService  {
/*
    @Autowired
    private IRezervacijaJpaDao iRezervacijaJpaDao;*/

    private final IRezervacijaJpaDao iRezervacijaJpaDao;

    @Autowired
    public RezervacijaJpaService( IRezervacijaJpaDao iRezervacijaJpaDao) {
        this.iRezervacijaJpaDao = iRezervacijaJpaDao;
    }

    public List<Rezervacija> getAllRezervations(){
        return iRezervacijaJpaDao.findAll();
    }

    public List<Rezervacija> getAllActivRezervations(){
        return iRezervacijaJpaDao.findAllActiveReservations();
    }

    public int addRezervacija(Rezervacija rezervacija){
        iRezervacijaJpaDao.save(rezervacija);
        return 1;
    }

    public Rezervacija addRezervacija2(Rezervacija rezervacija){
        return iRezervacijaJpaDao.save(rezervacija);
    }

    public int deleteAllRezervacije(){
        iRezervacijaJpaDao.deleteAll();
        return 1;
    }

    public int checkOverlaps(Rezervacija rezervacija){
       int a;
       try {
            a = iRezervacijaJpaDao.findAllOverlaps(rezervacija.getDateFrom(), rezervacija.getDateTo(), rezervacija.getApartmanId()).size();
       }
       catch (Exception e){
           a = 0;
       }

        return a;
    }

    public boolean checkOverlapsLocal(Rezervacija rezervacija){

        boolean overlaps = false;
        List<Rezervacija> rezervacijasRepo = iRezervacijaJpaDao.findAllActiveReservations();
        for(Rezervacija rezervacijaRepo : rezervacijasRepo)
        {
            if( (rezervacijaRepo.getDateFrom().isBefore(rezervacija.getDateTo())) && (rezervacijaRepo.getDateTo().isAfter(rezervacija.getDateFrom())) && rezervacijaRepo.getApartmanId() == rezervacija.getApartmanId() ){
                overlaps = true;
            }
        }
        return overlaps;
    }

    public int editRezervacijaStatus(int id){
        iRezervacijaJpaDao.editRezervacijaStatus(id);
        return 1;
    }

}
