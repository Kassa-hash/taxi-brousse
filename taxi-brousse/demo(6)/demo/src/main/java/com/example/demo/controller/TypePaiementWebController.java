package com.example.demo.controller;

import com.example.demo.entity.TypePaiement;
import com.example.demo.service.TypePaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/types-paiement")
public class TypePaiementWebController {
    @Autowired
    private TypePaiementService typePaiementService;

    @GetMapping
    public String afficherTypesPaiement(Model model) {
        model.addAttribute("types", typePaiementService.getAllTypePaiements());
        return "types-paiement/list";
    }

    @GetMapping("/form")
    public String afficherFormulaire(Model model) {
        model.addAttribute("type", new TypePaiement());
        return "types-paiement/form";
    }

    @PostMapping("/save")
    public String sauvegarder(@ModelAttribute TypePaiement type) {
        typePaiementService.createTypePaiement(type);
        return "redirect:/types-paiement";
    }

    @GetMapping("/{id}")
    public String afficherDetail(@PathVariable Integer id, Model model) {
        Optional<TypePaiement> type = typePaiementService.getTypePaiementById(id);
        if (type.isPresent()) {
            model.addAttribute("type", type.get());
            return "types-paiement/detail";
        }
        return "redirect:/types-paiement";
    }

    @GetMapping("/{id}/edit")
    public String afficherEditeur(@PathVariable Integer id, Model model) {
        Optional<TypePaiement> type = typePaiementService.getTypePaiementById(id);
        if (type.isPresent()) {
            model.addAttribute("type", type.get());
            return "types-paiement/form";
        }
        return "redirect:/types-paiement";
    }

    @PostMapping("/{id}/update")
    public String mettreAJour(@PathVariable Integer id, @ModelAttribute TypePaiement type) {
        typePaiementService.updateTypePaiement(id, type);
        return "redirect:/types-paiement/" + id;
    }

    @PostMapping("/{id}/delete")
    public String supprimer(@PathVariable Integer id) {
        typePaiementService.deleteTypePaiement(id);
        return "redirect:/types-paiement";
    }
}
