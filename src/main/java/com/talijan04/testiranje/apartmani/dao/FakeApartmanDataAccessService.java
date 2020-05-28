package com.talijan04.testiranje.apartmani.dao;

import com.talijan04.testiranje.apartmani.model.Apartman;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("fake")
public class FakeApartmanDataAccessService implements IApartmanDao {

    private static List<Apartman> DB = new ArrayList<>();

    @Override
    public List<Apartman> selectAllApartmans() {
        return DB;
    }

    @Override
    public Optional<Apartman> selectApartmaneById(int id) {
        return DB.stream().filter(apartman -> apartman.getId() == id).findFirst();
    }

    @Override
    public int insertApartman(Apartman apartman) {
        DB.add(new Apartman(apartman.getId(), apartman.getNaziv(), apartman.getCena(), ""));
        System.out.println(DB.size() + " " + apartman.getId() + " " + apartman.getNaziv() + " " + apartman.getCena());
        return 1;
    }

    @Override
    public int deleteApartmanById(int id) {
        Optional<Apartman> apartmanMaybe = selectApartmaneById(id);
        if (apartmanMaybe.isEmpty()) {
            return 0;
        }
        DB.remove(apartmanMaybe.get());
        return 1;
    }

    @Override
    public int updateApartmanById(int id, Apartman apartmanUpdate) {
        return selectApartmaneById(id).map(apartman -> {
            int indexOfApartmanToUpdate = DB.indexOf(apartman);
            if (indexOfApartmanToUpdate >= 0) {
                //DB.set(indexOfApartmanToUpdate, apartmanUpdate);
                DB.set(indexOfApartmanToUpdate, new Apartman(id, apartman.getNaziv(), apartman.getCena(), apartmanUpdate.getSlika()));
                return 1;
            }
            return 0;
        })
                .orElse(0);

    }


}
