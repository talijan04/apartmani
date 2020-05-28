package com.talijan04.testiranje.apartmani.dao;

import com.talijan04.testiranje.apartmani.model.Obracun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface IObracunJpaDao extends JpaRepository<Obracun, Integer> {

    @Query("SELECT o FROM Obracun o WHERE o.ukupanBrojNocenja = 1")
    List<Obracun> findAllJednoNocenje();
}
