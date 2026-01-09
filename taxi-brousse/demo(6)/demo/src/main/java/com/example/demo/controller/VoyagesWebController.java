package com.example.demo.controller;

import com.example.demo.entity.Voyage;
import com.example.demo.service.VoyageService;
import com.example.demo.service.ModeleVoyageService;
import com.example.demo.service.VoitureService;
import com.example.demo.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/voyages")
public class VoyagesWebController {

    @Autowired
    private VoyageService voyageService;
    
    @Autowired
    private ModeleVoyageService modeleVoyageService;
    
    @Autowired
    private VoitureService voitureService;
    
    @Autowired
    private EmployeService employeService;

    @GetMapping
    public String listVoyages(Model model) {
        model.addAttribute("voyages", voyageService.getAllVoyages());
        return "voyages/list";
    }
    
    @GetMapping("/{id}")
    public String viewVoyage(@PathVariable int id, Model model) {
        var voyage = voyageService.getVoyageById(id);
        if (voyage.isPresent()) {
            model.addAttribute("voyage", voyage.get());
            return "voyages/detail";
        }
        return "redirect:/voyages";
    }
    
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("voyage", new Voyage());
        model.addAttribute("modeles", modeleVoyageService.getAllModeleVoyages());
        model.addAttribute("voitures", voitureService.getAllVoitures());
        model.addAttribute("employes", employeService.getAllEmployes());
        return "voyages/form";
    }
    
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model) {
        var voyage = voyageService.getVoyageById(id);
        if (voyage.isPresent()) {
            model.addAttribute("voyage", voyage.get());
            model.addAttribute("modeles", modeleVoyageService.getAllModeleVoyages());
            model.addAttribute("voitures", voitureService.getAllVoitures());
            model.addAttribute("employes", employeService.getAllEmployes());
            return "voyages/form";
        }
        return "redirect:/voyages";
    }
    
    @PostMapping("/create")
    public String createVoyage(@ModelAttribute Voyage voyage) {
        voyageService.saveVoyage(voyage);
        return "redirect:/voyages";
    }
    
    @PostMapping("/{id}/update")
    public String updateVoyage(@PathVariable int id, @ModelAttribute Voyage voyage) {
        voyage.setIdVoyage(id);
        voyageService.updateVoyage(id, voyage);
        return "redirect:/voyages";
    }
    
    @GetMapping("/{id}/delete")
    public String deleteVoyage(@PathVariable int id) {
        voyageService.deleteVoyage(id);
        return "redirect:/voyages";
    }
}

