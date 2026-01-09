package com.example.demo.controller;

import com.example.demo.service.VoitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VoituresWebController {

    @Autowired
    private VoitureService voitureService;

    @GetMapping("/voitures")
    public String listVoitures(Model model) {
        model.addAttribute("voitures", voitureService.getAllVoitures());
        return "voitures/list";
    }
}
