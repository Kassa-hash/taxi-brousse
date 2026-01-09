package com.example.demo.controller;

import com.example.demo.entity.TypeVoyage;
import com.example.demo.service.TypeVoyageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/type-voyages")
@CrossOrigin(origins = "*")
public class TypeVoyageController {

    @Autowired
    private TypeVoyageService typeVoyageService;

    @GetMapping
    public ResponseEntity<List<TypeVoyage>> getAllTypeVoyages() {
        List<TypeVoyage> typeVoyages = typeVoyageService.getAllTypeVoyages();
        return ResponseEntity.ok(typeVoyages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeVoyage> getTypeVoyageById(@PathVariable int id) {
        Optional<TypeVoyage> typeVoyage = typeVoyageService.getTypeVoyageById(id);
        return typeVoyage.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TypeVoyage> createTypeVoyage(@RequestBody TypeVoyage typeVoyage) {
        TypeVoyage savedTypeVoyage = typeVoyageService.saveTypeVoyage(typeVoyage);
        return ResponseEntity.ok(savedTypeVoyage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypeVoyage> updateTypeVoyage(@PathVariable int id, @RequestBody TypeVoyage typeVoyage) {
        TypeVoyage updatedTypeVoyage = typeVoyageService.updateTypeVoyage(id, typeVoyage);
        if (updatedTypeVoyage != null) {
            return ResponseEntity.ok(updatedTypeVoyage);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTypeVoyage(@PathVariable int id) {
        if (typeVoyageService.deleteTypeVoyage(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
