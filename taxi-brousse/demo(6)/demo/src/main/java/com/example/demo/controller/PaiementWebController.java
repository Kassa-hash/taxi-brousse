package com.example.demo.controller;

import com.example.demo.entity.Paiement;
import com.example.demo.service.PaiementService;
import com.example.demo.service.TypePaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/paiements")
public class PaiementWebController {
    @Autowired
    private PaiementService paiementService;

    @Autowired
    private TypePaiementService typePaiementService;

    @GetMapping
    public String afficherPaiements(Model model) {
        model.addAttribute("paiements", paiementService.getAllPaiements());
        return "paiements/list";
    }

    @GetMapping("/form")
    public String afficherFormulaire(Model model) {
        model.addAttribute("paiement", new Paiement());
        model.addAttribute("types", typePaiementService.getAllTypePaiements());
        return "paiements/form";
    }

    @PostMapping("/save")
    public String sauvegarder(@ModelAttribute Paiement paiement) {
        paiementService.createPaiement(paiement);
        return "redirect:/paiements";
    }

    @GetMapping("/{id}")
    public String afficherDetail(@PathVariable Integer id, Model model) {
        Optional<Paiement> paiement = paiementService.getPaiementById(id);
        if (paiement.isPresent()) {
            model.addAttribute("paiement", paiement.get());
            return "paiements/detail";
        }
        return "redirect:/paiements";
    }

    @GetMapping("/{id}/edit")
    public String afficherEditeur(@PathVariable Integer id, Model model) {
        Optional<Paiement> paiement = paiementService.getPaiementById(id);
        if (paiement.isPresent()) {
            model.addAttribute("paiement", paiement.get());
            model.addAttribute("types", typePaiementService.getAllTypePaiements());
            return "paiements/form";
        }
        return "redirect:/paiements";
    }

    @PostMapping("/{id}/update")
    public String mettreAJour(@PathVariable Integer id, @ModelAttribute Paiement paiement) {
        paiementService.updatePaiement(id, paiement);
        return "redirect:/paiements/" + id;
    }

    @PostMapping("/{id}/delete")
    public String supprimer(@PathVariable Integer id) {
        paiementService.deletePaiement(id);
        return "redirect:/paiements";
    }
}
