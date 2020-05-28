package com.talijan04.testiranje.apartmani.api;

import com.talijan04.testiranje.apartmani.model.Apartman;
import com.talijan04.testiranje.apartmani.service.ApartmanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ApartmanController.class)
@ActiveProfiles("test")
class ApartmanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApartmanService apartmanService;

    private List<Apartman> apartmanList;

    @BeforeEach
    void setUp() {
        this.apartmanList = new ArrayList<>();
        this.apartmanList.add(new Apartman(101, "Apartman1", 2000,"img/picture1.jpg"));
        this.apartmanList.add(new Apartman(102, "Apartman2", 2000,"img/picture2.jpg"));
        this.apartmanList.add(new Apartman(103, "Apartman3", 2000,"img/picture3.jpg"));
    }

    @Test
    void getAllApartmans() throws Exception {

        given(apartmanService.getAllApartmans()).willReturn(apartmanList);

        this.mockMvc.perform(get("http://localhost:8080/api/v1/apartman"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.size()",is(apartmanList.size())));

    }

    @Test
    void getApartmanById() throws Exception {
        final int apartmanId = 1;
        final Apartman apartman = new Apartman(1, "Apartman1", 2000,"img/picture1.jpg");

        given(apartmanService.getApartmanById(apartmanId)).willReturn(Optional.of(apartman));

    this.mockMvc.perform(get("api/v1/apartman/{id}", apartmanId))
            .andExpect(status().isOk())
            .andExpect((ResultMatcher) jsonPath("$.naziv", is(apartman.getNaziv())));

    }

    @Test
    void addApartmana() {
    }

    @Test
    void deleteApartmanById() {
    }

    @Test
    void updateApartmanById() {
    }
}