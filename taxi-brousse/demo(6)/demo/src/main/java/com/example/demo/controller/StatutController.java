package com.example.demo.controller;

import com.example.demo.entity.Statut;
import com.example.demo.service.StatutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/statuts")
@CrossOrigin(origins = "*")
public class StatutController {

    @Autowired
    private StatutService statutService;

    @GetMapping
    public ResponseEntity<List<Statut>> getAllStatuts() {
        List<Statut> statuts = statutService.getAllStatuts();
        return ResponseEntity.ok(statuts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Statut> getStatutById(@PathVariable int id) {
        Optional<Statut> statut = statutService.getStatutById(id);
        return statut.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Statut> createStatut(@RequestBody Statut statut) {
        Statut savedStatut = statutService.saveStatut(statut);
        return ResponseEntity.ok(savedStatut);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Statut> updateStatut(@PathVariable int id, @RequestBody Statut statut) {
        Statut updatedStatut = statutService.updateStatut(id, statut);
        if (updatedStatut != null) {
            return ResponseEntity.ok(updatedStatut);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatut(@PathVariable int id) {
        if (statutService.deleteStatut(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
