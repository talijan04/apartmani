package com.talijan04.testiranje.apartmani.service;

import com.talijan04.testiranje.apartmani.dao.IApartmanDao;
import com.talijan04.testiranje.apartmani.model.Apartman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApartmanService {

    private final IApartmanDao iApartmanDao;

    @Autowired
    public ApartmanService(@Qualifier("postgres") IApartmanDao iApartmanDao) {
        this.iApartmanDao = iApartmanDao;
    }

    public List<Apartman> getAllApartmans(){
        return iApartmanDao.selectAllApartmans();
    }

    public Optional<Apartman> getApartmanById(int id){
        return iApartmanDao.selectApartmaneById(id);
    }

    public int addApartman(Apartman apartman){

        return iApartmanDao.insertApartman(apartman);
    }

    public int deleteApartman(int id){
        return iApartmanDao.deleteApartmanById(id);
    }

    public int updateApartman(int id, Apartman apartmanUpdate){
        return iApartmanDao.updateApartmanById(id, apartmanUpdate);
    }
}
