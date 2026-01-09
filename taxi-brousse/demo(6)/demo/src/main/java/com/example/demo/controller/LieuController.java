package com.example.demo.controller;

import com.example.demo.entity.Lieu;
import com.example.demo.service.LieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lieux")
@CrossOrigin(origins = "*")
public class LieuController {

    @Autowired
    private LieuService lieuService;

    @GetMapping
    public ResponseEntity<List<Lieu>> getAllLieux() {
        List<Lieu> lieux = lieuService.getAllLieux();
        return ResponseEntity.ok(lieux);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lieu> getLieuById(@PathVariable int id) {
        Optional<Lieu> lieu = lieuService.getLieuById(id);
        return lieu.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Lieu> createLieu(@RequestBody Lieu lieu) {
        Lieu savedLieu = lieuService.saveLieu(lieu);
        return ResponseEntity.ok(savedLieu);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lieu> updateLieu(@PathVariable int id, @RequestBody Lieu lieu) {
        Lieu updatedLieu = lieuService.updateLieu(id, lieu);
        if (updatedLieu != null) {
            return ResponseEntity.ok(updatedLieu);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLieu(@PathVariable int id) {
        if (lieuService.deleteLieu(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
