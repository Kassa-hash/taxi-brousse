package com.example.demo.controller;

import com.example.demo.entity.GareRoutiere;
import com.example.demo.service.GareRoutiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/gares")
public class GareRoutiereWebController {
    @Autowired
    private GareRoutiereService gareRoutiereService;

    @GetMapping
    public String afficherGares(Model model) {
        model.addAttribute("gares", gareRoutiereService.getAllGareRoutiere());
        return "gares/list";
    }

    @GetMapping("/form")
    public String afficherFormulaire(Model model) {
        model.addAttribute("gare", new GareRoutiere());
        return "gares/form";
    }

    @PostMapping("/save")
    public String sauvegarder(@ModelAttribute GareRoutiere gare) {
        gareRoutiereService.createGareRoutiere(gare);
        return "redirect:/gares";
    }

    @GetMapping("/{id}")
    public String afficherDetail(@PathVariable Integer id, Model model) {
        Optional<GareRoutiere> gare = gareRoutiereService.getGareRoutiereById(id);
        if (gare.isPresent()) {
            model.addAttribute("gare", gare.get());
            return "gares/detail";
        }
        return "redirect:/gares";
    }

    @GetMapping("/{id}/edit")
    public String afficherEditeur(@PathVariable Integer id, Model model) {
        Optional<GareRoutiere> gare = gareRoutiereService.getGareRoutiereById(id);
        if (gare.isPresent()) {
            model.addAttribute("gare", gare.get());
            return "gares/form";
        }
        return "redirect:/gares";
    }

    @PostMapping("/{id}/update")
    public String mettreAJour(@PathVariable Integer id, @ModelAttribute GareRoutiere gare) {
        gareRoutiereService.updateGareRoutiere(id, gare);
        return "redirect:/gares/" + id;
    }

    @PostMapping("/{id}/delete")
    public String supprimer(@PathVariable Integer id) {
        gareRoutiereService.deleteGareRoutiere(id);
        return "redirect:/gares";
    }
}
