package com.example.demo.controller;

import com.example.demo.entity.TypeVoiture;
import com.example.demo.service.TypeVoitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/type-voitures")
@CrossOrigin(origins = "*")
public class TypeVoitureController {

    @Autowired
    private TypeVoitureService typeVoitureService;

    @GetMapping
    public ResponseEntity<List<TypeVoiture>> getAllTypeVoitures() {
        List<TypeVoiture> typeVoitures = typeVoitureService.getAllTypeVoitures();
        return ResponseEntity.ok(typeVoitures);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeVoiture> getTypeVoitureById(@PathVariable int id) {
        Optional<TypeVoiture> typeVoiture = typeVoitureService.getTypeVoitureById(id);
        return typeVoiture.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TypeVoiture> createTypeVoiture(@RequestBody TypeVoiture typeVoiture) {
        TypeVoiture savedTypeVoiture = typeVoitureService.saveTypeVoiture(typeVoiture);
        return ResponseEntity.ok(savedTypeVoiture);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypeVoiture> updateTypeVoiture(@PathVariable int id, @RequestBody TypeVoiture typeVoiture) {
        TypeVoiture updatedTypeVoiture = typeVoitureService.updateTypeVoiture(id, typeVoiture);
        if (updatedTypeVoiture != null) {
            return ResponseEntity.ok(updatedTypeVoiture);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTypeVoiture(@PathVariable int id) {
        if (typeVoitureService.deleteTypeVoiture(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
