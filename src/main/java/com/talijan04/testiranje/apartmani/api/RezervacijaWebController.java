package com.talijan04.testiranje.apartmani.api;

import com.talijan04.testiranje.apartmani.dao.IObracunJpaDao;
import com.talijan04.testiranje.apartmani.model.Apartman;
import com.talijan04.testiranje.apartmani.model.Obracun;
import com.talijan04.testiranje.apartmani.model.Rezervacija;
import com.talijan04.testiranje.apartmani.service.ApartmanService;
import com.talijan04.testiranje.apartmani.service.NotificationService;
import com.talijan04.testiranje.apartmani.service.RezervacijaJpaService;
import com.talijan04.testiranje.apartmani.service.RezervacijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
public class RezervacijaWebController implements WebMvcConfigurer{

    @Autowired
    private RezervacijaService rezervacijaService;

    @Autowired
    private ApartmanService apartmanService;

    @Autowired
    private IObracunJpaDao iObracunJpaDao;

    @Autowired
    private RezervacijaJpaService rezervacijaJpaService;

    @Autowired
    private NotificationService notificationService;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @RequestMapping("/rezervacije")
    public String showListRezervacijeForm(Model model) {
        List<Rezervacija> listRezervacija =  rezervacijaService.getAllRezervations();
       //List<Rezervacija> data = rezervacijaJpaService.getAllRezervations();
        List<Rezervacija> data = rezervacijaJpaService.getAllActivRezervations();
        model.addAttribute("listRezervacija", listRezervacija);
        model.addAttribute("data", data);
        System.out.println("AppController->showListRezervacijaForm()");
        return "rezervacije.html";
    }

    @RequestMapping("/newrezervacija1")
    public String showRezervacijeForm(Model model) {
        List<Apartman> listApartmani = apartmanService.getAllApartmans();
        model.addAttribute("listApartmani", listApartmani);
        model.addAttribute("broj", 0);
        Rezervacija rezervacija = new Rezervacija();
        //rezervacija.setDateFrom(LocalDate.parse("05/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        rezervacija.setDateFrom(LocalDate.now());
        //rezervacija.setDateTo(LocalDate.parse("06/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        rezervacija.setDateTo(LocalDate.now().plusDays(1));
        model.addAttribute("rezervacija",rezervacija);
        System.out.println("AppController->showRezervacijeForm()");
        return "new_rezervacija.html";
    }

    @RequestMapping(value = "/saverezervacija", method = RequestMethod.POST)
    public String saveRezervacija(@ModelAttribute("rezervacija") @Valid Rezervacija rezervacija, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            List<Apartman> listApartmani = apartmanService.getAllApartmans();
            model.addAttribute("listApartmani", listApartmani);
            System.out.println(" --- --- --- Imamo gresaka :)  - greske od validacije");
            return "new_rezervacija.html";
        }
        int broj =  rezervacijaJpaService.checkOverlaps(rezervacija);
        System.out.println("DatumOd: " + rezervacija.getDateFrom()+" - DatumDo: "+ rezervacija.getDateTo() + " Pronasao: "+ broj );

        if (broj != 0 ){
            List<Apartman> listApartmani = apartmanService.getAllApartmans();
            model.addAttribute("listApartmani", listApartmani);
            model.addAttribute("broj", broj);
            System.out.println(" --- --- --- Imamo gresaka :)  - broj preklapanja nije nula");
            return "new_rezervacija.html";
        }

        //rezervacijaService.addRezervacija(rezervacija);
        rezervacijaJpaService.addRezervacija(rezervacija);
        Obracun  obracun = new Obracun( rezervacija.getId(), rezervacija.getUkupanBrojNocenja(), 2000, 100 );
        iObracunJpaDao.save(obracun);


        // Salje mail obavestenja
        try {
            //notificationService.sendNotification();
        } catch (MailException e) {
            e.printStackTrace();
        }
        return "redirect:/results";
    }

    @GetMapping("/sendemail")
    public String sendEmail(){
        try {
            notificationService.sendNotification();
        } catch (MailException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }


    @RequestMapping("/edit-status/{id}")
    public String showEditApartmanPage(@PathVariable(name = "id") int id) {
          rezervacijaJpaService.editRezervacijaStatus(id);
        return "redirect:/rezervacije";
    }

    @RequestMapping("/admincenter")
    public String getAdminCenter(Model model) {
        System.out.println("AppController->adminCenter()");
        return "admin";
    }

    @RequestMapping("/admincenter-delete")
    public String deleteAllRezervacije(Model model) {
        rezervacijaJpaService.deleteAllRezervacije();
        System.out.println("AppController->deleteAllRezervacije()");
        return "redirect:/admincenter";
    }



}
