package com.talijan04.testiranje.apartmani.dao;

import com.talijan04.testiranje.apartmani.model.Apartman;

import java.util.List;
import java.util.Optional;

public interface IApartmanDao {

    List<Apartman> selectAllApartmans();

    Optional<Apartman> selectApartmaneById(int id);

    int insertApartman(Apartman apartman);

    int deleteApartmanById(int id);

    int updateApartmanById(int id, Apartman apartman);


}





