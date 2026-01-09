package com.example.demo.controller;

import com.example.demo.entity.TypePaiement;
import com.example.demo.service.TypePaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/type-paiements")
@CrossOrigin(origins = "*")
public class TypePaiementController {

    @Autowired
    private TypePaiementService typePaiementService;

    @GetMapping
    public ResponseEntity<List<TypePaiement>> getAllTypePaiements() {
        List<TypePaiement> typePaiements = typePaiementService.getAllTypePaiements();
        return ResponseEntity.ok(typePaiements);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypePaiement> getTypePaiementById(@PathVariable int id) {
        Optional<TypePaiement> typePaiement = typePaiementService.getTypePaiementById(id);
        return typePaiement.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TypePaiement> createTypePaiement(@RequestBody TypePaiement typePaiement) {
        TypePaiement savedTypePaiement = typePaiementService.saveTypePaiement(typePaiement);
        return ResponseEntity.ok(savedTypePaiement);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypePaiement> updateTypePaiement(@PathVariable int id, @RequestBody TypePaiement typePaiement) {
        TypePaiement updatedTypePaiement = typePaiementService.updateTypePaiement(id, typePaiement);
        if (updatedTypePaiement != null) {
            return ResponseEntity.ok(updatedTypePaiement);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTypePaiement(@PathVariable int id) {
        if (typePaiementService.deleteTypePaiement(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
