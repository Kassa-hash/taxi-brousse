package com.example.demo.controller;

import com.example.demo.entity.GareRoutiere;
import com.example.demo.service.GareRoutiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/gares-routieres")
public class GareRoutiereController {
    @Autowired
    private GareRoutiereService gareRoutiereService;

    @GetMapping
    public ResponseEntity<List<GareRoutiere>> getAllGareRoutiere() {
        List<GareRoutiere> gares = gareRoutiereService.getAllGareRoutiere();
        return ResponseEntity.ok(gares);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GareRoutiere> getGareRoutiereById(@PathVariable Integer id) {
        Optional<GareRoutiere> gare = gareRoutiereService.getGareRoutiereById(id);
        return gare.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GareRoutiere> createGareRoutiere(@RequestBody GareRoutiere gareRoutiere) {
        GareRoutiere createdGare = gareRoutiereService.createGareRoutiere(gareRoutiere);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGare);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GareRoutiere> updateGareRoutiere(@PathVariable Integer id, @RequestBody GareRoutiere gareRoutiere) {
        GareRoutiere updatedGare = gareRoutiereService.updateGareRoutiere(id, gareRoutiere);
        if (updatedGare != null) {
            return ResponseEntity.ok(updatedGare);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGareRoutiere(@PathVariable Integer id) {
        gareRoutiereService.deleteGareRoutiere(id);
        return ResponseEntity.noContent().build();
    }
}
