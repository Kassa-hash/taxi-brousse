package com.example.demo.controller;

import com.example.demo.entity.Etat;
import com.example.demo.service.EtatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/etats")
@CrossOrigin(origins = "*")
public class EtatController {

    @Autowired
    private EtatService etatService;

    @GetMapping
    public ResponseEntity<List<Etat>> getAllEtats() {
        List<Etat> etats = etatService.getAllEtats();
        return ResponseEntity.ok(etats);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Etat> getEtatById(@PathVariable int id) {
        Optional<Etat> etat = etatService.getEtatById(id);
        return etat.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Etat> createEtat(@RequestBody Etat etat) {
        Etat savedEtat = etatService.saveEtat(etat);
        return ResponseEntity.ok(savedEtat);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Etat> updateEtat(@PathVariable int id, @RequestBody Etat etat) {
        Etat updatedEtat = etatService.updateEtat(id, etat);
        if (updatedEtat != null) {
            return ResponseEntity.ok(updatedEtat);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEtat(@PathVariable int id) {
        if (etatService.deleteEtat(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
