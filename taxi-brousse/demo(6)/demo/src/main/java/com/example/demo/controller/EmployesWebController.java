package com.example.demo.controller;

import com.example.demo.entity.Employe;
import com.example.demo.service.EmployeService;
import com.example.demo.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employes")
public class EmployesWebController {

    @Autowired
    private EmployeService employeService;
    
    @Autowired
    private GenreService genreService;

    @GetMapping
    public String listEmployes(Model model) {
        model.addAttribute("employes", employeService.getAllEmployes());
        return "employes/list";
    }
    
    @GetMapping("/{id}")
    public String viewEmploye(@PathVariable int id, Model model) {
        var employe = employeService.getEmployeById(id);
        if (employe.isPresent()) {
            model.addAttribute("employe", employe.get());
            return "employes/detail";
        }
        return "redirect:/employes";
    }
    
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("employe", new Employe());
        model.addAttribute("genres", genreService.getAllGenres());
        return "employes/form";
    }
    
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model) {
        var employe = employeService.getEmployeById(id);
        if (employe.isPresent()) {
            model.addAttribute("employe", employe.get());
            model.addAttribute("genres", genreService.getAllGenres());
            return "employes/form";
        }
        return "redirect:/employes";
    }
    
    @PostMapping("/create")
    public String createEmploye(@ModelAttribute Employe employe) {
        employeService.saveEmploye(employe);
        return "redirect:/employes";
    }
    
    @PostMapping("/{id}/update")
    public String updateEmploye(@PathVariable int id, @ModelAttribute Employe employe) {
        employe.setId(id);
        employeService.updateEmploye(id, employe);
        return "redirect:/employes";
    }
    
    @GetMapping("/{id}/delete")
    public String deleteEmploye(@PathVariable int id) {
        employeService.deleteEmploye(id);
        return "redirect:/employes";
    }
}

