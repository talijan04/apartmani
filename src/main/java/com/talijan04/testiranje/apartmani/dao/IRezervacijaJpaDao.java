package com.talijan04.testiranje.apartmani.dao;

import com.talijan04.testiranje.apartmani.model.Rezervacija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface IRezervacijaJpaDao extends JpaRepository<Rezervacija, Integer> {

    @Query("SELECT u FROM Rezervacija u WHERE u.status = true")
    List<Rezervacija> findAllActiveReservations();

    @Query("SELECT u FROM Rezervacija u WHERE ((u.dateFrom < :datumDo) and (u.dateTo > :datumOd )) and u.apartmanId = :apartmanId  and (u.status =true)")
    List<Rezervacija> findAllOverlaps(
            @Param("datumOd") LocalDate datumOd,
            @Param("datumDo") LocalDate datumDo,
            @Param("apartmanId") int apartmanId);

    @Transactional
    @Modifying
    //@Modifying(clearAutomatically = true)
    @Query("UPDATE Rezervacija u SET u.status = false WHERE u.id = :id")
    void editRezervacijaStatus(@Param("id") int id);

}