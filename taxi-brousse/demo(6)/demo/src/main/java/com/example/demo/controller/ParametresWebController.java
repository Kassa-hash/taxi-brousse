package com.example.demo.controller;

import com.example.demo.service.ParametreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ParametresWebController {

    @Autowired
    private ParametreService parametreService;

    @GetMapping("/parametres")
    public String listParametres(Model model) {
        model.addAttribute("parametres", parametreService.getAllParametres());
        return "parametres/list";
    }
}
