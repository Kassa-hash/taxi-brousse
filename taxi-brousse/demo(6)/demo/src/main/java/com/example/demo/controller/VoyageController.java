package com.example.demo.controller;

import com.example.demo.entity.Voyage;
import com.example.demo.service.VoyageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/voyages")
@CrossOrigin(origins = "*")
public class VoyageController {

    @Autowired
    private VoyageService voyageService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Voyage>> getAllVoyages() {
        List<Voyage> voyages = voyageService.getAllVoyages();
        return ResponseEntity.ok(voyages);
    }

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String viewVoyages(Model model) {
        model.addAttribute("voyages", voyageService.getAllVoyages());
        return "voyages/list";
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Voyage> getVoyageById(@PathVariable int id) {
        Optional<Voyage> voyage = voyageService.getVoyageById(id);
        return voyage.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Voyage> createVoyage(@RequestBody Voyage voyage) {
        Voyage savedVoyage = voyageService.saveVoyage(voyage);
        return ResponseEntity.ok(savedVoyage);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Voyage> updateVoyage(@PathVariable int id, @RequestBody Voyage voyage) {
        Voyage updatedVoyage = voyageService.updateVoyage(id, voyage);
        if (updatedVoyage != null) {
            return ResponseEntity.ok(updatedVoyage);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Void> deleteVoyage(@PathVariable int id) {
        if (voyageService.deleteVoyage(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
