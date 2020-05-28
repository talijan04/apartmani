package com.talijan04.testiranje.apartmani.api;

import com.talijan04.testiranje.apartmani.model.Apartman;
import com.talijan04.testiranje.apartmani.model.Rezervacija;
import com.talijan04.testiranje.apartmani.service.ApartmanService;
import com.talijan04.testiranje.apartmani.service.RezervacijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
public class AppRezervacijaService {

    @Autowired
    private RezervacijaService rezervacijaService;

    @Autowired
    private ApartmanService apartmanService;

    @RequestMapping("/rezervacije33333333")
    public String showListRezervacijeForm(Model model) {
        List<Rezervacija> listRezervacija =  rezervacijaService.getAllRezervations();
        model.addAttribute("listRezervacija", listRezervacija);
        System.out.println("AppController->showListRezervacijaForm()");
        return "rezervacije.html";
    }

    @RequestMapping("/newrezervacija33333333")
    public String showRezervacijeForm(Model model) {
        List<Apartman> listApartmani = apartmanService.getAllApartmans();
        model.addAttribute("listApartmani", listApartmani);
        Rezervacija rezervacija = new Rezervacija();
        model.addAttribute("rezervacija",rezervacija);
        model.addAttribute("standardDate", new Date());
        model.addAttribute("localDateTime", LocalDateTime.now());
        model.addAttribute("localDate", LocalDate.now());
        model.addAttribute("timestamp", Instant.now());
        System.out.println("AppController->showRezervacijeForm()");
        return "new_rezervacija.html";
    }

    @RequestMapping(value = "/saverezervacija33333333", method = RequestMethod.POST)
    public String saveRezervacija(@ModelAttribute("rezervacija") Rezervacija rezervacija) {
        System.out.println(" --- 0: insertRezervacija->getDateFrom(): "+rezervacija.getDateFrom());
        rezervacijaService.addRezervacija(rezervacija);
        return "redirect:/";
    }
}
