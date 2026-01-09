package com.example.demo.controller;

import com.example.demo.service.EntretientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EntretientsWebController {

    @Autowired
    private EntretientService entretientService;

    @GetMapping("/entretients")
    public String listEntretients(Model model) {
        model.addAttribute("entretients", entretientService.getAllEntretients());
        return "entretients/list";
    }
}
