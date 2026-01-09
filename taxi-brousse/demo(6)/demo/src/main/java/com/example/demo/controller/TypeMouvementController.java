package com.example.demo.controller;

import com.example.demo.entity.TypeMouvement;
import com.example.demo.service.TypeMouvementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/type-mouvements")
@CrossOrigin(origins = "*")
public class TypeMouvementController {

    @Autowired
    private TypeMouvementService typeMouvementService;

    @GetMapping
    public ResponseEntity<List<TypeMouvement>> getAllTypeMouvements() {
        List<TypeMouvement> typeMouvements = typeMouvementService.getAllTypeMouvements();
        return ResponseEntity.ok(typeMouvements);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeMouvement> getTypeMouvementById(@PathVariable int id) {
        Optional<TypeMouvement> typeMouvement = typeMouvementService.getTypeMouvementById(id);
        return typeMouvement.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TypeMouvement> createTypeMouvement(@RequestBody TypeMouvement typeMouvement) {
        TypeMouvement savedTypeMouvement = typeMouvementService.saveTypeMouvement(typeMouvement);
        return ResponseEntity.ok(savedTypeMouvement);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypeMouvement> updateTypeMouvement(@PathVariable int id, @RequestBody TypeMouvement typeMouvement) {
        TypeMouvement updatedTypeMouvement = typeMouvementService.updateTypeMouvement(id, typeMouvement);
        if (updatedTypeMouvement != null) {
            return ResponseEntity.ok(updatedTypeMouvement);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTypeMouvement(@PathVariable int id) {
        if (typeMouvementService.deleteTypeMouvement(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
