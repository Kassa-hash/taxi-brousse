package com.example.demo.controller;

import com.example.demo.entity.Voiture;
import com.example.demo.service.VoitureService;
import com.example.demo.service.EtatService;
import com.example.demo.service.MarqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/voitures")
public class VoituresWebController {

    @Autowired
    private VoitureService voitureService;
    
    @Autowired
    private EtatService etatService;
    
    @Autowired
    private MarqueService marqueService;

    @GetMapping
    public String listVoitures(Model model) {
        model.addAttribute("voitures", voitureService.getAllVoitures());
        return "voitures/list";
    }
    
    @GetMapping("/{id}")
    public String viewVoiture(@PathVariable int id, Model model) {
        var voiture = voitureService.getVoitureById(id);
        if (voiture.isPresent()) {
            model.addAttribute("voiture", voiture.get());
            return "voitures/detail";
        }
        return "redirect:/voitures";
    }
    
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("voiture", new Voiture());
        model.addAttribute("etats", etatService.getAllEtats());
        model.addAttribute("marques", marqueService.getAllMarques());
        return "voitures/form";
    }
    
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model) {
        var voiture = voitureService.getVoitureById(id);
        if (voiture.isPresent()) {
            model.addAttribute("voiture", voiture.get());
            model.addAttribute("etats", etatService.getAllEtats());
            model.addAttribute("marques", marqueService.getAllMarques());
            return "voitures/form";
        }
        return "redirect:/voitures";
    }
    
    @PostMapping("/create")
    public String createVoiture(@ModelAttribute Voiture voiture) {
        voitureService.saveVoiture(voiture);
        return "redirect:/voitures";
    }
    
    @PostMapping("/{id}/update")
    public String updateVoiture(@PathVariable int id, @ModelAttribute Voiture voiture) {
        voiture.setIdVoiture(id);
        voitureService.updateVoiture(id, voiture);
        return "redirect:/voitures";
    }
    
    @GetMapping("/{id}/delete")
    public String deleteVoiture(@PathVariable int id) {
        voitureService.deleteVoiture(id);
        return "redirect:/voitures";
    }
}

