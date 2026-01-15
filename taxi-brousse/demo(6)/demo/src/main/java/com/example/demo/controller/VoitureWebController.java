package com.example.demo.controller;

import com.example.demo.entity.Voiture;
import com.example.demo.entity.Voyage;
import com.example.demo.repository.TrajetCategoriePrixRepository;
import com.example.demo.repository.VoyageRepository;
import com.example.demo.service.VoitureService;
import com.example.demo.service.VoyageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/voitures")
public class VoitureWebController {

    @Autowired 
    private VoitureService voitureService;

    @Autowired 
    private VoyageService voyageService;

    @Autowired 
    private TrajetCategoriePrixRepository trajetPrixRepo;

    @Autowired 
    private VoyageRepository voyageRepo;

    // --- Gestion Classique des Voitures ---

    @GetMapping
    public String afficherVoitures(Model model) {
        model.addAttribute("voitures", voitureService.getAllVoitures());
        return "voitures/list";
    }

    @GetMapping("/form")
    public String afficherFormulaire(Model model) {
        model.addAttribute("voiture", new Voiture());
        return "voitures/form";
    }

    @PostMapping("/save")
    public String sauvegarder(@ModelAttribute Voiture voiture) {
        voitureService.createVoiture(voiture);
        return "redirect:/voitures";
    }

    @GetMapping("/{id}")
    public String afficherDetail(@PathVariable Integer id, Model model) {
        Optional<Voiture> voiture = voitureService.getVoitureById(id);
        if (voiture.isPresent()) {
            model.addAttribute("voiture", voiture.get());
            return "voitures/detail";
        }
        return "redirect:/voitures";
    }

    @GetMapping("/{id}/edit")
    public String afficherEditeur(@PathVariable Integer id, Model model) {
        Optional<Voiture> voiture = voitureService.getVoitureById(id);
        if (voiture.isPresent()) {
            model.addAttribute("voiture", voiture.get());
            return "voitures/form";
        }
        return "redirect:/voitures";
    }

    @PostMapping("/{id}/update")
    public String mettreAJour(@PathVariable Integer id, @ModelAttribute Voiture voiture) {
        voitureService.updateVoiture(id, voiture);
        return "redirect:/voitures/" + id;
    }

    @PostMapping("/{id}/delete")
    public String supprimer(@PathVariable Integer id) {
        voitureService.deleteVoiture(id);
        return "redirect:/voitures";
    }

    // --- Nouvelles fonctionnalités : Tarifs et Rentabilité ---

    // Note : Ici l'ID passé doit être l'ID d'un Voyage pour trouver les tarifs associés
    @GetMapping("/voyage/{idVoyage}/tarifs")
    public String voirTarifs(@PathVariable Integer idVoyage, Model model) {
        Voyage voyage = voyageRepo.findById(idVoyage)
                .orElseThrow(() -> new RuntimeException("Voyage introuvable avec l'ID : " + idVoyage));
        
        model.addAttribute("listeTrajetPrix", trajetPrixRepo.findByVoyage(voyage));
        return "tarifs"; // Assurez-vous que tarifs.html existe
    }

    // Affiche la valeur maximale qu'une voiture peut générer pour un voyage précis
    @GetMapping("/voyage/{idVoyage}/rentabilite")
    public String voirRentabilite(@PathVariable Integer idVoyage, Model model) {
        Voyage voyage = voyageRepo.findById(idVoyage)
                .orElseThrow(() -> new RuntimeException("Voyage introuvable avec l'ID : " + idVoyage));
        
        double max = voyageService.calculerValeurMaximale(voyage);

        model.addAttribute("voyage", voyage);
        model.addAttribute("valeurMax", max);
        return "valeur-max"; // Assurez-vous que valeur-max.html existe
    }
}