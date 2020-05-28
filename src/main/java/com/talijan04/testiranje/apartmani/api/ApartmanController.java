package com.talijan04.testiranje.apartmani.api;

import com.talijan04.testiranje.apartmani.model.Apartman;
import com.talijan04.testiranje.apartmani.service.ApartmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RequestMapping("api/v1/apartman")
@RestController
public class ApartmanController {

    private final ApartmanService apartmanService;

    @Autowired
    public ApartmanController(ApartmanService apartmanService) {
        this.apartmanService = apartmanService;
    }

    @GetMapping
    public List<Apartman> getAllApartmans() {
        return apartmanService.getAllApartmans();
    }

    @GetMapping(path = "{id}")
    public Apartman getApartmanById(@PathVariable("id") int id) {
        return apartmanService.getApartmanById(id).orElse(null);
    }

    @PostMapping
    public void addApartmana(@RequestBody Apartman apartman) {
        apartmanService.addApartman(apartman);
    }

    @DeleteMapping(path = "{id}")
    public void deleteApartmanById(@PathVariable("id") int id){
        apartmanService.deleteApartman(id);
    }

    @PutMapping(path = "{id}")
    public void updateApartmanById(@PathVariable("id") int id, @RequestBody Apartman apartmanUpdate){
        apartmanService.updateApartman(id, apartmanUpdate);
    }
}
