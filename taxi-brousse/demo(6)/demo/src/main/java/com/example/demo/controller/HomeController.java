package com.example.demo.controller;

import com.example.demo.service.AchatService;
import com.example.demo.service.VoyageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private AchatService achatService;

    @Autowired
    private VoyageService voyageService;

    @GetMapping("/")
    public String home(Model model) {
        // Ajouter les données pour l'affichage
        model.addAttribute("achats", achatService.getAllAchats());
        model.addAttribute("chiffreAffaires", achatService.calculateChiffreAffaires());
        model.addAttribute("voyagesDisponibles", voyageService.getAllVoyages());
        return "index";
    }
}
