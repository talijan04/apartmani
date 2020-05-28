package com.talijan04.testiranje.apartmani.service;

import com.talijan04.testiranje.apartmani.dao.IRezervacijaJpaDao;
import com.talijan04.testiranje.apartmani.model.Rezervacija;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RezervacijaJpaServiceMockitoTest {
    private IRezervacijaJpaDao repository = Mockito.mock(IRezervacijaJpaDao.class);

    @Test   //B1
    void checkOverlapsLocal_ShouldReturnFalse_IfEnteredPeriodBefore() {
        RezervacijaJpaService rezervacijaJpaService = new RezervacijaJpaService(repository);

        Rezervacija rezervacija = new Rezervacija(1, 1, "Aleksandar", "Jovanović", "00254781", "talijan04@gmail.com", LocalDate.parse("05/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("07/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        List<Rezervacija> rezervacijas = new ArrayList();
        rezervacijas.add(new Rezervacija(1, 1, "Aleksandar", "Jovanović", "00254781", "talijan04@gmail.com", LocalDate.parse("08/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("12/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));

        Mockito.when(repository.findAllActiveReservations())
                .thenReturn(rezervacijas);

        boolean expectedResult = false;
        boolean actualResult = rezervacijaJpaService.checkOverlapsLocal(rezervacija);
        Assertions.assertEquals(expectedResult, actualResult);

        Mockito.verify(repository).findAllActiveReservations();

    }

    @Test    //B2
    void checkOverlapsLocal_ShouldReturnFalse_IfEnteredPeriodBeforeAndTouching() {
        RezervacijaJpaService rezervacijaJpaService = new RezervacijaJpaService(repository);

        Rezervacija rezervacija = new Rezervacija(1, 1, "Aleksandar", "Jovanović", "00254781", "talijan04@gmail.com", LocalDate.parse("05/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("08/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        List<Rezervacija> rezervacijas = new ArrayList();
        rezervacijas.add(new Rezervacija(1, 1, "Aleksandar", "Jovanović", "00254781", "talijan04@gmail.com", LocalDate.parse("08/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("12/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));

        Mockito.when(repository.findAllActiveReservations())
                .thenReturn(rezervacijas);

        boolean expectedResult = false;
        boolean actualResult = rezervacijaJpaService.checkOverlapsLocal(rezervacija);
        Assertions.assertEquals(expectedResult, actualResult);

        Mockito.verify(repository).findAllActiveReservations();
    }

    @Test    //B3
    void checkOverlapsLocal_ShouldReturnTrue_IfEnteredPeriodBeforeAndInsideInterval() {
        RezervacijaJpaService rezervacijaJpaService = new RezervacijaJpaService(repository);

        Rezervacija rezervacija = new Rezervacija(1, 1, "Aleksandar", "Jovanović", "00254781", "talijan04@gmail.com", LocalDate.parse("05/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("09/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        List<Rezervacija> rezervacijas = new ArrayList();
        rezervacijas.add(new Rezervacija(1, 1, "Aleksandar", "Jovanović", "00254781", "talijan04@gmail.com", LocalDate.parse("08/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("12/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));

        Mockito.when(repository.findAllActiveReservations())
                .thenReturn(rezervacijas);

        boolean expectedResult = true;
        boolean actualResult = rezervacijaJpaService.checkOverlapsLocal(rezervacija);
        Assertions.assertEquals(expectedResult, actualResult);

        Mockito.verify(repository).findAllActiveReservations();
    }

    @Test   //B5
    void checkOverlapsLocal_ShouldReturnTrue_IfEnteredPeriodBeforeAndTouvhingEndOfInterval() {
        RezervacijaJpaService rezervacijaJpaService = new RezervacijaJpaService(repository);

        Rezervacija rezervacija = new Rezervacija(1, 1, "Aleksandar", "Jovanović", "00254781", "talijan04@gmail.com", LocalDate.parse("05/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("12/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        List<Rezervacija> rezervacijas = new ArrayList();
        rezervacijas.add(new Rezervacija(1, 1, "Aleksandar", "Jovanović", "00254781", "talijan04@gmail.com", LocalDate.parse("08/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("12/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));

        Mockito.when(repository.findAllActiveReservations())
                .thenReturn(rezervacijas);

        boolean expectedResult = true;
        boolean actualResult = rezervacijaJpaService.checkOverlapsLocal(rezervacija);
        Assertions.assertEquals(expectedResult, actualResult);

        Mockito.verify(repository).findAllActiveReservations();
    }

    @Test   //B7
    void checkOverlapsLocal_ShouldReturnTrue_IfEnteredPeriodBeforeAndAfterEndOfInterval() {
        RezervacijaJpaService rezervacijaJpaService = new RezervacijaJpaService(repository);

        Rezervacija rezervacija = new Rezervacija(1, 1, "Aleksandar", "Jovanović", "00254781", "talijan04@gmail.com", LocalDate.parse("05/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("15/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        List<Rezervacija> rezervacijas = new ArrayList();
        rezervacijas.add(new Rezervacija(1, 1, "Aleksandar", "Jovanović", "00254781", "talijan04@gmail.com", LocalDate.parse("08/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("12/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));

        Mockito.when(repository.findAllActiveReservations())
                .thenReturn(rezervacijas);

        boolean expectedResult = true;
        boolean actualResult = rezervacijaJpaService.checkOverlapsLocal(rezervacija);
        Assertions.assertEquals(expectedResult, actualResult);

        Mockito.verify(repository).findAllActiveReservations();
    }

    @Test   //B9
    void checkOverlapsLocal_ShouldReturnTrue_IfEnteredPeriodTouchingBeginOfIntervalAndAfterEndOfInterval() {
        RezervacijaJpaService rezervacijaJpaService = new RezervacijaJpaService(repository);

        Rezervacija rezervacija = new Rezervacija(1, 1, "Aleksandar", "Jovanović", "00254781", "talijan04@gmail.com", LocalDate.parse("08/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("15/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        List<Rezervacija> rezervacijas = new ArrayList();
        rezervacijas.add(new Rezervacija(1, 1, "Aleksandar", "Jovanović", "00254781", "talijan04@gmail.com", LocalDate.parse("08/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("12/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));

        Mockito.when(repository.findAllActiveReservations())
                .thenReturn(rezervacijas);

        boolean expectedResult = true;
        boolean actualResult = rezervacijaJpaService.checkOverlapsLocal(rezervacija);
        Assertions.assertEquals(expectedResult, actualResult);

        Mockito.verify(repository).findAllActiveReservations();
    }

    @Test   //B10
    void checkOverlapsLocal_ShouldReturnTrue_IfEnteredPeriodInsideOfIntervalAndAfterEndOfInterval() {
        RezervacijaJpaService rezervacijaJpaService = new RezervacijaJpaService(repository);

        Rezervacija rezervacija = new Rezervacija(1, 1, "Aleksandar", "Jovanović", "00254781", "talijan04@gmail.com", LocalDate.parse("09/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("15/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        List<Rezervacija> rezervacijas = new ArrayList();
        rezervacijas.add(new Rezervacija(1, 1, "Aleksandar", "Jovanović", "00254781", "talijan04@gmail.com", LocalDate.parse("08/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("12/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));

        Mockito.when(repository.findAllActiveReservations())
                .thenReturn(rezervacijas);

        boolean expectedResult = true;
        boolean actualResult = rezervacijaJpaService.checkOverlapsLocal(rezervacija);
        Assertions.assertEquals(expectedResult, actualResult);

        Mockito.verify(repository).findAllActiveReservations();
    }

    @Test   //B12
    void checkOverlapsLocal_ShouldReturnFalse_IfEnteredPeriodTouchingEndOfIntervalAndAfterEndOfInterval() {
        RezervacijaJpaService rezervacijaJpaService = new RezervacijaJpaService(repository);

        Rezervacija rezervacija = new Rezervacija(1, 1, "Aleksandar", "Jovanović", "00254781", "talijan04@gmail.com", LocalDate.parse("12/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("15/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        List<Rezervacija> rezervacijas = new ArrayList();
        rezervacijas.add(new Rezervacija(1, 1, "Aleksandar", "Jovanović", "00254781", "talijan04@gmail.com", LocalDate.parse("08/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("12/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));

        Mockito.when(repository.findAllActiveReservations())
                .thenReturn(rezervacijas);

        boolean expectedResult = false;
        boolean actualResult = rezervacijaJpaService.checkOverlapsLocal(rezervacija);
        Assertions.assertEquals(expectedResult, actualResult);

        Mockito.verify(repository).findAllActiveReservations();
    }

    @Test   //B13
    void checkOverlapsLocal_ShouldReturnFalse_IfEnteredPeriodIsAfterInterval() {
        RezervacijaJpaService rezervacijaJpaService = new RezervacijaJpaService(repository);

        Rezervacija rezervacija = new Rezervacija(1, 1, "Aleksandar", "Jovanović", "00254781", "talijan04@gmail.com", LocalDate.parse("12/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("15/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        List<Rezervacija> rezervacijas = new ArrayList();
        rezervacijas.add(new Rezervacija(1, 1, "Aleksandar", "Jovanović", "00254781", "talijan04@gmail.com", LocalDate.parse("08/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("12/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));

        Mockito.when(repository.findAllActiveReservations())
                .thenReturn(rezervacijas);

        boolean expectedResult = false;
        boolean actualResult = rezervacijaJpaService.checkOverlapsLocal(rezervacija);
        Assertions.assertEquals(expectedResult, actualResult);

        Mockito.verify(repository).findAllActiveReservations();
    }

    @Test   //B15
    void checkOverlapsLocal_ShouldReturnTrue_IfEnteredPeriodBegineInsideAndFinishInsideOfTheInterval() {
        RezervacijaJpaService rezervacijaJpaService = new RezervacijaJpaService(repository);

        Rezervacija rezervacija = new Rezervacija(1, 1, "Aleksandar", "Jovanović", "00254781", "talijan04@gmail.com", LocalDate.parse("09/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("11/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        List<Rezervacija> rezervacijas = new ArrayList();
        rezervacijas.add(new Rezervacija(1, 1, "Aleksandar", "Jovanović", "00254781", "talijan04@gmail.com", LocalDate.parse("08/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("12/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));

        Mockito.when(repository.findAllActiveReservations())
                .thenReturn(rezervacijas);

        boolean expectedResult = true;
        boolean actualResult = rezervacijaJpaService.checkOverlapsLocal(rezervacija);
        Assertions.assertEquals(expectedResult, actualResult);

        Mockito.verify(repository).findAllActiveReservations();
    }

    @Test   //B16
    void checkOverlapsLocal_ShouldReturnTrue_IfEnteredPeriodTouchingBeginOfTheIntervalAndFinishInsideOfTheInterval() {
        RezervacijaJpaService rezervacijaJpaService = new RezervacijaJpaService(repository);

        Rezervacija rezervacija = new Rezervacija(1, 1, "Aleksandar", "Jovanović", "00254781", "talijan04@gmail.com", LocalDate.parse("08/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("11/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        List<Rezervacija> rezervacijas = new ArrayList();
        rezervacijas.add(new Rezervacija(1, 1, "Aleksandar", "Jovanović", "00254781", "talijan04@gmail.com", LocalDate.parse("08/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("12/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));

        Mockito.when(repository.findAllActiveReservations())
                .thenReturn(rezervacijas);

        boolean expectedResult = true;
        boolean actualResult = rezervacijaJpaService.checkOverlapsLocal(rezervacija);
        Assertions.assertEquals(expectedResult, actualResult);

        Mockito.verify(repository).findAllActiveReservations();
    }

    @Test   //B17
    void checkOverlapsLocal_ShouldReturnTrue_IfEnteredPeriodInsideOfTheIntervalAndTouchingEndOfTheInterval() {
        RezervacijaJpaService rezervacijaJpaService = new RezervacijaJpaService(repository);

        Rezervacija rezervacija = new Rezervacija(1, 1, "Aleksandar", "Jovanović", "00254781", "talijan04@gmail.com", LocalDate.parse("09/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("12/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        List<Rezervacija> rezervacijas = new ArrayList();
        rezervacijas.add(new Rezervacija(1, 1, "Aleksandar", "Jovanović", "00254781", "talijan04@gmail.com", LocalDate.parse("08/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("12/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));

        Mockito.when(repository.findAllActiveReservations())
                .thenReturn(rezervacijas);

        boolean expectedResult = true;
        boolean actualResult = rezervacijaJpaService.checkOverlapsLocal(rezervacija);
        Assertions.assertEquals(expectedResult, actualResult);

        Mockito.verify(repository).findAllActiveReservations();
    }

    @Test   //B18
    void checkOverlapsLocal_ShouldReturnTrue_IfEnteredPeriodTouchingBeginAndTheEndOfTheInterval() {
        RezervacijaJpaService rezervacijaJpaService = new RezervacijaJpaService(repository);

        Rezervacija rezervacija = new Rezervacija(1, 1, "Aleksandar", "Jovanović", "00254781", "talijan04@gmail.com", LocalDate.parse("08/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("12/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        List<Rezervacija> rezervacijas = new ArrayList();
        rezervacijas.add(new Rezervacija(1, 1, "Aleksandar", "Jovanović", "00254781", "talijan04@gmail.com", LocalDate.parse("08/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("12/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));

        Mockito.when(repository.findAllActiveReservations())
                .thenReturn(rezervacijas);

        boolean expectedResult = true;
        boolean actualResult = rezervacijaJpaService.checkOverlapsLocal(rezervacija);
        Assertions.assertEquals(expectedResult, actualResult);

        Mockito.verify(repository).findAllActiveReservations();
    }

    @Test
    void checkOverlaps_ShouldReturn0_IfEnteredPeriodBefore() {
        RezervacijaJpaService rezervacijaJpaService = new RezervacijaJpaService(repository);

        Rezervacija rezervacija = new Rezervacija(1, 1, "Aleksandar", "Jovanović", "00254781", "talijan04@gmail.com", LocalDate.parse("05/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("10/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        List<Rezervacija> rezervacijas = new ArrayList();
        //rezervacijas.add(new Rezervacija(1, 1, "Aleksandar", "Jovanović", "00254781", "talijan04@gmail.com", LocalDate.parse("08/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("12/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));

        Mockito.when(repository.findAllOverlaps(rezervacija.getDateFrom(), rezervacija.getDateTo(), rezervacija.getApartmanId()))
                .thenReturn(rezervacijas);


        int expectedResult = 0;
        int actualResult = rezervacijaJpaService.checkOverlaps(rezervacija);
        Assertions.assertEquals(expectedResult, actualResult);

        Mockito.verify(repository).findAllOverlaps(rezervacija.getDateFrom(), rezervacija.getDateTo(), rezervacija.getApartmanId());
    }

    @Test
    void checkOverlaps_ShouldReturn1_IfEnteredPeriodStartBeforeAndFinishInsideOfTheInterval() {
        RezervacijaJpaService rezervacijaJpaService = new RezervacijaJpaService(repository);

        Rezervacija rezervacija = new Rezervacija(1, 1, "Aleksandar", "Jovanović", "00254781", "talijan04@gmail.com", LocalDate.parse("05/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("10/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        List<Rezervacija> rezervacijas = new ArrayList();
        rezervacijas.add(new Rezervacija(1, 1, "Aleksandar", "Jovanović", "00254781", "talijan04@gmail.com", LocalDate.parse("08/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalDate.parse("12/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));

        Mockito.when(repository.findAllOverlaps(rezervacija.getDateFrom(), rezervacija.getDateTo(), rezervacija.getApartmanId()))
                .thenReturn(rezervacijas);

        int expectedResult = 1;
        int actualResult = rezervacijaJpaService.checkOverlaps(rezervacija);
        Assertions.assertEquals(expectedResult, actualResult);

        Mockito.verify(repository).findAllOverlaps(rezervacija.getDateFrom(), rezervacija.getDateTo(), rezervacija.getApartmanId());
    }
}
