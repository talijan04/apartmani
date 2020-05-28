package com.talijan04.testiranje.apartmani.api;

import com.talijan04.testiranje.apartmani.dao.IRezervacijaJpaDao;
import com.talijan04.testiranje.apartmani.model.Rezervacija;
import com.talijan04.testiranje.apartmani.service.RezervacijaJpaService;
import com.talijan04.testiranje.apartmani.service.RezervacijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;

@Controller
public class WebController implements WebMvcConfigurer {

    @Autowired
    ////private IRezervacijaJpaDao iRezervacijaJpaDao;
    private RezervacijaJpaService rezervacijaJpaService;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping("/greeting")
    public String showGreeting(Model model, @RequestParam(defaultValue = "1") int page) {
        //model.addAttribute("data", iRezervacijaJpaDao.findAll(new PageRequest( page, 4)));
      ////  model.addAttribute("data", iRezervacijaJpaDao.findAll());
       model.addAttribute("data", rezervacijaJpaService.getAllRezervations());
        //Rezervacija rezervacija = new Rezervacija(10,1,"Aleksandar","Jovanovic", "","",null, null);
        //model.addAttribute("data", rezervacija );
        return "greeting";
    }

    @GetMapping("/newrezervacija")
    public String showForm(Rezervacija rezervacija) {
        return "form";
    }

    @PostMapping("/newrezervacija")
    public String checkPersonInfo(@Valid Rezervacija rezervacija, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            System.out.println(" --- --- --- Imamo gresaka :)");
            return "form";
        }

        return "redirect:/results";
    }


}
