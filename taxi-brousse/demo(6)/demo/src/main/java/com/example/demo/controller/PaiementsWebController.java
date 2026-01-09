package com.example.demo.controller;

import com.example.demo.entity.Paiement;
import com.example.demo.service.PaiementService;
import com.example.demo.service.TypePaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/paiements")
public class PaiementsWebController {

    @Autowired
    private PaiementService paiementService;
    
    @Autowired
    private TypePaiementService typePaiementService;

    @GetMapping
    public String listPaiements(Model model) {
        model.addAttribute("paiements", paiementService.getAllPaiements());
        return "paiements/list";
    }
    
    @GetMapping("/{id}")
    public String viewPaiement(@PathVariable int id, Model model) {
        var paiement = paiementService.getPaiementById(id);
        if (paiement.isPresent()) {
            model.addAttribute("paiement", paiement.get());
            return "paiements/detail";
        }
        return "redirect:/paiements";
    }
    
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("paiement", new Paiement());
        model.addAttribute("types", typePaiementService.getAllTypePaiements());
        return "paiements/form";
    }
    
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model) {
        var paiement = paiementService.getPaiementById(id);
        if (paiement.isPresent()) {
            model.addAttribute("paiement", paiement.get());
            model.addAttribute("types", typePaiementService.getAllTypePaiements());
            return "paiements/form";
        }
        return "redirect:/paiements";
    }
    
    @PostMapping("/create")
    public String createPaiement(@ModelAttribute Paiement paiement) {
        paiementService.savePaiement(paiement);
        return "redirect:/paiements";
    }
    
    @PostMapping("/{id}/update")
    public String updatePaiement(@PathVariable int id, @ModelAttribute Paiement paiement) {
        paiement.setIdPaiement(id);
        paiementService.updatePaiement(id, paiement);
        return "redirect:/paiements";
    }
    
    @GetMapping("/{id}/delete")
    public String deletePaiement(@PathVariable int id) {
        paiementService.deletePaiement(id);
        return "redirect:/paiements";
    }
}

