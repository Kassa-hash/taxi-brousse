package com.example.demo.controller;

import com.example.demo.entity.Parametre;
import com.example.demo.service.ParametreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/parametres")
public class ParametresWebController {

    @Autowired
    private ParametreService parametreService;

    @GetMapping
    public String listParametres(Model model) {
        model.addAttribute("parametres", parametreService.getAllParametres());
        return "parametres/list";
    }
    
    @GetMapping("/{id}")
    public String viewParametre(@PathVariable int id, Model model) {
        var parametre = parametreService.getParametreById(id);
        if (parametre.isPresent()) {
            model.addAttribute("parametre", parametre.get());
            return "parametres/detail";
        }
        return "redirect:/parametres";
    }
    
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("parametre", new Parametre());
        return "parametres/form";
    }
    
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model) {
        var parametre = parametreService.getParametreById(id);
        if (parametre.isPresent()) {
            model.addAttribute("parametre", parametre.get());
            return "parametres/form";
        }
        return "redirect:/parametres";
    }
    
    @PostMapping("/create")
    public String createParametre(@ModelAttribute Parametre parametre) {
        parametreService.saveParametre(parametre);
        return "redirect:/parametres";
    }
    
    @PostMapping("/{id}/update")
    public String updateParametre(@PathVariable int id, @ModelAttribute Parametre parametre) {
        parametre.setIdParametre(id);
        parametreService.updateParametre(id, parametre);
        return "redirect:/parametres";
    }
    
    @GetMapping("/{id}/delete")
    public String deleteParametre(@PathVariable int id) {
        parametreService.deleteParametre(id);
        return "redirect:/parametres";
    }
}

