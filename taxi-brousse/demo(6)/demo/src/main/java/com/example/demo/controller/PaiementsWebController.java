package com.example.demo.controller;

import com.example.demo.service.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaiementsWebController {

    @Autowired
    private PaiementService paiementService;

    @GetMapping("/paiements")
    public String listPaiements(Model model) {
        model.addAttribute("paiements", paiementService.getAllPaiements());
        return "paiements/list";
    }
}
