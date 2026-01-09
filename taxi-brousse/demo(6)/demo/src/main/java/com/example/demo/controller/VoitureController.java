package com.example.demo.controller;

import com.example.demo.entity.Voiture;
import com.example.demo.service.VoitureService;
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
@RequestMapping("/api/voitures")
@CrossOrigin(origins = "*")
public class VoitureController {

    @Autowired
    private VoitureService voitureService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Voiture>> getAllVoitures() {
        List<Voiture> voitures = voitureService.getAllVoitures();
        return ResponseEntity.ok(voitures);
    }

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String viewVoitures(Model model) {
        model.addAttribute("voitures", voitureService.getAllVoitures());
        return "voitures/list";
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Voiture> getVoitureById(@PathVariable int id) {
        Optional<Voiture> voiture = voitureService.getVoitureById(id);
        return voiture.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Voiture> createVoiture(@RequestBody Voiture voiture) {
        Voiture savedVoiture = voitureService.saveVoiture(voiture);
        return ResponseEntity.ok(savedVoiture);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Voiture> updateVoiture(@PathVariable int id, @RequestBody Voiture voiture) {
        Voiture updatedVoiture = voitureService.updateVoiture(id, voiture);
        if (updatedVoiture != null) {
            return ResponseEntity.ok(updatedVoiture);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Void> deleteVoiture(@PathVariable int id) {
        if (voitureService.deleteVoiture(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
