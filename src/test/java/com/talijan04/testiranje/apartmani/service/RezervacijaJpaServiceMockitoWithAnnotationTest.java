package com.talijan04.testiranje.apartmani.service;

import com.talijan04.testiranje.apartmani.dao.IRezervacijaJpaDao;
import com.talijan04.testiranje.apartmani.model.Rezervacija;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class RezervacijaJpaServiceMockitoWithAnnotationTest {

    @Mock
    private IRezervacijaJpaDao iRezervacijaJpaDao;

    @InjectMocks
    private RezervacijaJpaService rezervacijaJpaService;

    @Test
    void checkOverlaps_ShouldReturn0_IfEnteredPeriodBefore() {

        Rezervacija rezervacija = new Rezervacija(1, 1, "Aleksandar", "Jovanović", "00254781", "talijan04@gmail.com", LocalDate.parse("05/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("07/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        List<Rezervacija> rezervacijas = new ArrayList();

        given(iRezervacijaJpaDao.findAllOverlaps(rezervacija.getDateFrom(), rezervacija.getDateTo(), rezervacija.getApartmanId())).willReturn(rezervacijas);

        int expectedResult = 0;
        int actualResult = rezervacijaJpaService.checkOverlaps(rezervacija);
        Assertions.assertEquals(expectedResult, actualResult);

        Mockito.verify(iRezervacijaJpaDao).findAllOverlaps(rezervacija.getDateFrom(), rezervacija.getDateTo(), rezervacija.getApartmanId());
    }

    @Test
    void checkOverlaps_ShouldReturn1_IfEnteredPeriodStartBeforeAndFinishInsideOfThePeriod() {

        Rezervacija rezervacija = new Rezervacija(1, 1, "Aleksandar", "Jovanović", "00254781", "talijan04@gmail.com", LocalDate.parse("05/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("10/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        List<Rezervacija> rezervacijas = new ArrayList();
        rezervacijas.add(new Rezervacija(1, 1, "Aleksandar", "Jovanović", "00254781", "talijan04@gmail.com", LocalDate.parse("08/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("12/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));

        given(iRezervacijaJpaDao.findAllOverlaps(rezervacija.getDateFrom(), rezervacija.getDateTo(), rezervacija.getApartmanId())).willReturn(rezervacijas);

        int expectedResult = 1;
        int actualResult = rezervacijaJpaService.checkOverlaps(rezervacija);
        Assertions.assertEquals(expectedResult, actualResult);

        Mockito.verify(iRezervacijaJpaDao).findAllOverlaps(rezervacija.getDateFrom(), rezervacija.getDateTo(), rezervacija.getApartmanId());
    }

    @Test
    void addRezervacija2_ShouldSaveRezervationSaccessfully() {

        final Rezervacija rezervacija =  new Rezervacija(1, 1, "Aleksandar", "Jovanović", "00254781", "talijan04@gmail.com", LocalDate.parse("08/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("12/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        given(iRezervacijaJpaDao.save(rezervacija)).willAnswer( invocation -> invocation.getArgument(0) );
        Rezervacija savedRezervacija = rezervacijaJpaService.addRezervacija2(rezervacija);
        assertThat(savedRezervacija).isNotNull();
        Mockito.verify(iRezervacijaJpaDao).save(any(Rezervacija.class));
    }

    @Test
    void findAll_ShouldReturnAllRezervations() {

        List<Rezervacija> rezervacijas = new ArrayList();
        rezervacijas.add(new Rezervacija(1, 1, "Aleksandar", "Jovanović", "00254781", "talijan04@gmail.com", LocalDate.parse("08/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("12/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        given(iRezervacijaJpaDao.findAll()).willReturn(rezervacijas);
        List<Rezervacija> expectedResult = rezervacijaJpaService.getAllRezervations();
        Assertions.assertEquals(expectedResult, rezervacijas);
        Mockito.verify(iRezervacijaJpaDao).findAll();
    }


}
