package com.talijan04.testiranje.apartmani.api;

import com.talijan04.testiranje.apartmani.model.Apartman;
import com.talijan04.testiranje.apartmani.model.Rezervacija;
import com.talijan04.testiranje.apartmani.service.ApartmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class AppController {

    @Autowired
    private ApartmanService apartmanService;


    @RequestMapping("/")
    public String getAll(Model model) {
        List<Apartman> listApartmani = apartmanService.getAllApartmans();
        model.addAttribute("listApartmani", listApartmani);
        System.out.println("AppController->index()");
        return "index.html";
    }

    @RequestMapping("/list")
    public String showListApartmanForm(Model model) {
        List<Apartman> listApartmani = apartmanService.getAllApartmans();
        model.addAttribute("listApartmani", listApartmani);
        System.out.println("AppController->showListApartmanForm()");
        return "list_apartman.html";
    }


    @RequestMapping("/new")
    public String showNewApartmanForm(Model model) {
        Apartman apartman = new Apartman();
        model.addAttribute("apartman", apartman);
        System.out.println("AppController->showNewApartmanForm()");
        return "new_apartman.html";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveApartman(@ModelAttribute("apartman") Apartman apartman) {
        apartmanService.addApartman(apartman);
        return "redirect:/list";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateApartman(@ModelAttribute("apartman") Apartman apartman) {
        apartmanService.updateApartman(apartman.getId(), apartman);
        System.out.println("AppController->showUpdateApartman()");
        return "redirect:/list";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditApartmanPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_apartman");
        Optional<Apartman> apartman = apartmanService.getApartmanById(id);
        mav.addObject("apartman", apartman);
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") int id) {
        apartmanService.deleteApartman(id);
        return "redirect:/list";
    }



    @RequestMapping("/mylogin")
    public String myLogin() {
        return "mylogin.html";
    }


}
