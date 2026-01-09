package com.example.demo.controller;

import com.example.demo.service.VoyageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VoyagesWebController {

    @Autowired
    private VoyageService voyageService;

    @GetMapping("/voyages")
    public String listVoyages(Model model) {
        model.addAttribute("voyages", voyageService.getAllVoyages());
        return "voyages/list";
    }
}
