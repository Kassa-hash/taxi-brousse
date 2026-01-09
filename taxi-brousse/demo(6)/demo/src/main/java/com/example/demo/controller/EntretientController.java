package com.example.demo.controller;

import com.example.demo.entity.Entretient;
import com.example.demo.service.EntretientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/entretients")
@CrossOrigin(origins = "*")
public class EntretientController {

    @Autowired
    private EntretientService entretientService;

    @GetMapping
    public ResponseEntity<List<Entretient>> getAllEntretients() {
        List<Entretient> entretients = entretientService.getAllEntretients();
        return ResponseEntity.ok(entretients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entretient> getEntretientById(@PathVariable int id) {
        Optional<Entretient> entretient = entretientService.getEntretientById(id);
        return entretient.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Entretient> createEntretient(@RequestBody Entretient entretient) {
        Entretient savedEntretient = entretientService.saveEntretient(entretient);
        return ResponseEntity.ok(savedEntretient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entretient> updateEntretient(@PathVariable int id, @RequestBody Entretient entretient) {
        Entretient updatedEntretient = entretientService.updateEntretient(id, entretient);
        if (updatedEntretient != null) {
            return ResponseEntity.ok(updatedEntretient);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntretient(@PathVariable int id) {
        if (entretientService.deleteEntretient(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
