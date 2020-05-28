package com.talijan04.testiranje.apartmani.service;

import com.talijan04.testiranje.apartmani.dao.IRezervacijaDao;
import com.talijan04.testiranje.apartmani.dao.IRezervacijaJpaDao;
import com.talijan04.testiranje.apartmani.model.Apartman;
import com.talijan04.testiranje.apartmani.model.Rezervacija;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RezervacijaJpaServiceTest {

    private RezervacijaJpaService rezervacijaJpaService;

    @BeforeEach
    public void prepareRezervacijaJpaService() {
        rezervacijaJpaService = new RezervacijaJpaService(new IRezervacijaJpaDao() {
            @Override
            public List<Rezervacija> findAllActiveReservations() {
                return null;
            }

            @Override
            public List<Rezervacija> findAllOverlaps(LocalDate datumOd, LocalDate datumDo, int apartmanId) {

                List<Rezervacija> DB = new ArrayList<>();
                LocalDate IntervalBegine =  LocalDate.parse("08/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                LocalDate IntervalEnde =  LocalDate.parse("12/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                if (IntervalBegine.isBefore(datumDo) && (IntervalEnde.isAfter(datumOd))) {
                    DB.add(new Rezervacija(
                            1,
                            1,
                            "Aleksandar",
                            "Jovanović",
                            "00254781",
                            "talijan04@gmail.com",
                            LocalDate.parse("08/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                            LocalDate.parse("12/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
                }
                return DB;
            }

            @Override
            public void editRezervacijaStatus(int id) {

            }

            @Override
            public List<Rezervacija> findAll() {
                return null;
            }

            @Override
            public List<Rezervacija> findAll(Sort sort) {
                return null;
            }

            @Override
            public List<Rezervacija> findAllById(Iterable<Integer> iterable) {
                return null;
            }

            @Override
            public <S extends Rezervacija> List<S> saveAll(Iterable<S> iterable) {
                return null;
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends Rezervacija> S saveAndFlush(S s) {
                return null;
            }

            @Override
            public void deleteInBatch(Iterable<Rezervacija> iterable) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public Rezervacija getOne(Integer integer) {
                return null;
            }

            @Override
            public <S extends Rezervacija> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends Rezervacija> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public Page<Rezervacija> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Rezervacija> S save(S s) {
                return null;
            }

            @Override
            public Optional<Rezervacija> findById(Integer integer) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(Integer integer) {
                return false;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Integer integer) {

            }

            @Override
            public void delete(Rezervacija rezervacija) {

            }

            @Override
            public void deleteAll(Iterable<? extends Rezervacija> iterable) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public <S extends Rezervacija> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends Rezervacija> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Rezervacija> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends Rezervacija> boolean exists(Example<S> example) {
                return false;
            }
        });
    }

    @Test
    void checkOverlaps1() {

        Rezervacija rezervacija = new Rezervacija(1, 1, "Aleksandar", "Jovanović", "00254781", "talijan04@gmail.com", LocalDate.parse("05/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("08/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        int expectedResult = 0;
        int actualResult = rezervacijaJpaService.checkOverlaps(rezervacija);
        Assertions.assertEquals(expectedResult, actualResult);

    }

    @Test
    void checkOverlaps2() {

        Rezervacija rezervacija = new Rezervacija(1, 1, "Aleksandar", "Jovanović", "00254781", "talijan04@gmail.com", LocalDate.parse("05/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("15/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        int expectedResult = 1;
        int actualResult = rezervacijaJpaService.checkOverlaps(rezervacija);
        Assertions.assertEquals(expectedResult, actualResult);

    }
}