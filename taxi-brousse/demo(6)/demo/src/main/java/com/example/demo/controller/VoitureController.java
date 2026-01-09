package com.example.demo.controller;

import com.example.demo.entity.Voiture;
import com.example.demo.service.VoitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/voitures")
public class VoitureController {
    @Autowired
    private VoitureService voitureService;

    @GetMapping
    public ResponseEntity<List<Voiture>> getAllVoitures() {
        List<Voiture> voitures = voitureService.getAllVoitures();
        return ResponseEntity.ok(voitures);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Voiture> getVoitureById(@PathVariable Integer id) {
        Optional<Voiture> voiture = voitureService.getVoitureById(id);
        return voiture.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Voiture> createVoiture(@RequestBody Voiture voiture) {
        Voiture createdVoiture = voitureService.createVoiture(voiture);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVoiture);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Voiture> updateVoiture(@PathVariable Integer id, @RequestBody Voiture voiture) {
        Voiture updatedVoiture = voitureService.updateVoiture(id, voiture);
        if (updatedVoiture != null) {
            return ResponseEntity.ok(updatedVoiture);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoiture(@PathVariable Integer id) {
        voitureService.deleteVoiture(id);
        return ResponseEntity.noContent().build();
    }
}
