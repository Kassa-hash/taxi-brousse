package com.example.demo.controller;

import com.example.demo.entity.Marque;
import com.example.demo.service.MarqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/marques")
@CrossOrigin(origins = "*")
public class MarqueController {

    @Autowired
    private MarqueService marqueService;

    @GetMapping
    public ResponseEntity<List<Marque>> getAllMarques() {
        List<Marque> marques = marqueService.getAllMarques();
        return ResponseEntity.ok(marques);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Marque> getMarqueById(@PathVariable int id) {
        Optional<Marque> marque = marqueService.getMarqueById(id);
        return marque.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Marque> createMarque(@RequestBody Marque marque) {
        Marque savedMarque = marqueService.saveMarque(marque);
        return ResponseEntity.ok(savedMarque);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Marque> updateMarque(@PathVariable int id, @RequestBody Marque marque) {
        Marque updatedMarque = marqueService.updateMarque(id, marque);
        if (updatedMarque != null) {
            return ResponseEntity.ok(updatedMarque);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarque(@PathVariable int id) {
        if (marqueService.deleteMarque(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
