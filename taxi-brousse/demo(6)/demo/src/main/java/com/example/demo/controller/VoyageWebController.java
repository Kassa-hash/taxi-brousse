package com.example.demo.controller;

import com.example.demo.entity.Voyage;
import com.example.demo.entity.GareRoutiere;
import com.example.demo.service.VoyageService;
import com.example.demo.service.GareRoutiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/voyages")
public class VoyageWebController {
    @Autowired
    private VoyageService voyageService;

    @Autowired
    private GareRoutiereService gareRoutiereService;

    @GetMapping
    public String afficherVoyages(Model model) {
        model.addAttribute("voyages", voyageService.getAllVoyages());
        return "voyages/list";
    }

    @GetMapping("/form")
    public String afficherFormulaire(Model model) {
        model.addAttribute("voyage", new Voyage());
        model.addAttribute("gares", gareRoutiereService.getAllGareRoutiere());
        return "voyages/form";
    }

    @PostMapping("/save")
    public String sauvegarder(@ModelAttribute Voyage voyage) {
        voyageService.createVoyage(voyage);
        return "redirect:/voyages";
    }

    @GetMapping("/{id}")
    public String afficherDetail(@PathVariable Integer id, Model model) {
        Optional<Voyage> voyage = voyageService.getVoyageById(id);
        if (voyage.isPresent()) {
            model.addAttribute("voyage", voyage.get());
            return "voyages/detail";
        }
        return "redirect:/voyages";
    }

    @GetMapping("/{id}/edit")
    public String afficherEditeur(@PathVariable Integer id, Model model) {
        Optional<Voyage> voyage = voyageService.getVoyageById(id);
        if (voyage.isPresent()) {
            model.addAttribute("voyage", voyage.get());
            model.addAttribute("gares", gareRoutiereService.getAllGareRoutiere());
            return "voyages/form";
        }
        return "redirect:/voyages";
    }

    @PostMapping("/{id}/update")
    public String mettreAJour(@PathVariable Integer id, @ModelAttribute Voyage voyage) {
        voyageService.updateVoyage(id, voyage);
        return "redirect:/voyages/" + id;
    }

    @PostMapping("/{id}/delete")
    public String supprimer(@PathVariable Integer id) {
        voyageService.deleteVoyage(id);
        return "redirect:/voyages";
    }
}
