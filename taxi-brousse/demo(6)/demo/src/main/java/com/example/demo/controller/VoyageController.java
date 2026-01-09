package com.example.demo.controller;

import com.example.demo.entity.Voyage;
import com.example.demo.service.VoyageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/voyages")
public class VoyageController {
    @Autowired
    private VoyageService voyageService;

    @GetMapping
    public ResponseEntity<List<Voyage>> getAllVoyages() {
        List<Voyage> voyages = voyageService.getAllVoyages();
        return ResponseEntity.ok(voyages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Voyage> getVoyageById(@PathVariable Integer id) {
        Optional<Voyage> voyage = voyageService.getVoyageById(id);
        return voyage.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Voyage> createVoyage(@RequestBody Voyage voyage) {
        Voyage createdVoyage = voyageService.createVoyage(voyage);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVoyage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Voyage> updateVoyage(@PathVariable Integer id, @RequestBody Voyage voyage) {
        Voyage updatedVoyage = voyageService.updateVoyage(id, voyage);
        if (updatedVoyage != null) {
            return ResponseEntity.ok(updatedVoyage);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoyage(@PathVariable Integer id) {
        voyageService.deleteVoyage(id);
        return ResponseEntity.noContent().build();
    }
}
