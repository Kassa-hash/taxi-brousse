package com.example.demo.controller;

import com.example.demo.entity.Entretient;
import com.example.demo.service.EntretientService;
import com.example.demo.service.TypeMouvementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/entretients")
public class EntretientsWebController {

    @Autowired
    private EntretientService entretientService;
    
    @Autowired
    private TypeMouvementService typeMouvementService;

    @GetMapping
    public String listEntretients(Model model) {
        model.addAttribute("entretients", entretientService.getAllEntretients());
        return "entretients/list";
    }
    
    @GetMapping("/{id}")
    public String viewEntretient(@PathVariable int id, Model model) {
        var entretient = entretientService.getEntretientById(id);
        if (entretient.isPresent()) {
            model.addAttribute("entretient", entretient.get());
            return "entretients/detail";
        }
        return "redirect:/entretients";
    }
    
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("entretient", new Entretient());
        model.addAttribute("types", typeMouvementService.getAllTypeMouvements());
        return "entretients/form";
    }
    
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model) {
        var entretient = entretientService.getEntretientById(id);
        if (entretient.isPresent()) {
            model.addAttribute("entretient", entretient.get());
            model.addAttribute("types", typeMouvementService.getAllTypeMouvements());
            return "entretients/form";
        }
        return "redirect:/entretients";
    }
    
    @PostMapping("/create")
    public String createEntretient(@ModelAttribute Entretient entretient) {
        entretientService.saveEntretient(entretient);
        return "redirect:/entretients";
    }
    
    @PostMapping("/{id}/update")
    public String updateEntretient(@PathVariable int id, @ModelAttribute Entretient entretient) {
        entretient.setIdEntretient(id);
        entretientService.updateEntretient(id, entretient);
        return "redirect:/entretients";
    }
    
    @GetMapping("/{id}/delete")
    public String deleteEntretient(@PathVariable int id) {
        entretientService.deleteEntretient(id);
        return "redirect:/entretients";
    }
}

