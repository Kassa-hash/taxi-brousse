package com.example.demo.controller;

import com.example.demo.entity.ModeleVoyage;
import com.example.demo.service.ModeleVoyageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/modele-voyages")
@CrossOrigin(origins = "*")
public class ModeleVoyageController {

    @Autowired
    private ModeleVoyageService modeleVoyageService;

    @GetMapping
    public ResponseEntity<List<ModeleVoyage>> getAllModeleVoyages() {
        List<ModeleVoyage> modeleVoyages = modeleVoyageService.getAllModeleVoyages();
        return ResponseEntity.ok(modeleVoyages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModeleVoyage> getModeleVoyageById(@PathVariable int id) {
        Optional<ModeleVoyage> modeleVoyage = modeleVoyageService.getModeleVoyageById(id);
        return modeleVoyage.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ModeleVoyage> createModeleVoyage(@RequestBody ModeleVoyage modeleVoyage) {
        ModeleVoyage savedModeleVoyage = modeleVoyageService.saveModeleVoyage(modeleVoyage);
        return ResponseEntity.ok(savedModeleVoyage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModeleVoyage> updateModeleVoyage(@PathVariable int id, @RequestBody ModeleVoyage modeleVoyage) {
        ModeleVoyage updatedModeleVoyage = modeleVoyageService.updateModeleVoyage(id, modeleVoyage);
        if (updatedModeleVoyage != null) {
            return ResponseEntity.ok(updatedModeleVoyage);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModeleVoyage(@PathVariable int id) {
        if (modeleVoyageService.deleteModeleVoyage(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
