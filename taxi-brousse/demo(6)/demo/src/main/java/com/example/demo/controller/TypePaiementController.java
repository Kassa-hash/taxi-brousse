package com.example.demo.controller;

import com.example.demo.entity.TypePaiement;
import com.example.demo.service.TypePaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/types-paiement")
public class TypePaiementController {
    @Autowired
    private TypePaiementService typePaiementService;

    @GetMapping
    public ResponseEntity<List<TypePaiement>> getAllTypePaiements() {
        List<TypePaiement> types = typePaiementService.getAllTypePaiements();
        return ResponseEntity.ok(types);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypePaiement> getTypePaiementById(@PathVariable Integer id) {
        Optional<TypePaiement> type = typePaiementService.getTypePaiementById(id);
        return type.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TypePaiement> createTypePaiement(@RequestBody TypePaiement typePaiement) {
        TypePaiement createdType = typePaiementService.createTypePaiement(typePaiement);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypePaiement> updateTypePaiement(@PathVariable Integer id, @RequestBody TypePaiement typePaiement) {
        TypePaiement updatedType = typePaiementService.updateTypePaiement(id, typePaiement);
        if (updatedType != null) {
            return ResponseEntity.ok(updatedType);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTypePaiement(@PathVariable Integer id) {
        typePaiementService.deleteTypePaiement(id);
        return ResponseEntity.noContent().build();
    }
}
