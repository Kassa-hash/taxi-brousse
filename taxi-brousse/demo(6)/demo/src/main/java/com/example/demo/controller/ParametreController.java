package com.example.demo.controller;

import com.example.demo.entity.Parametre;
import com.example.demo.service.ParametreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/parametres")
@CrossOrigin(origins = "*")
public class ParametreController {

    @Autowired
    private ParametreService parametreService;

    @GetMapping
    public ResponseEntity<List<Parametre>> getAllParametres() {
        List<Parametre> parametres = parametreService.getAllParametres();
        return ResponseEntity.ok(parametres);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parametre> getParametreById(@PathVariable int id) {
        Optional<Parametre> parametre = parametreService.getParametreById(id);
        return parametre.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Parametre> createParametre(@RequestBody Parametre parametre) {
        Parametre savedParametre = parametreService.saveParametre(parametre);
        return ResponseEntity.ok(savedParametre);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Parametre> updateParametre(@PathVariable int id, @RequestBody Parametre parametre) {
        Parametre updatedParametre = parametreService.updateParametre(id, parametre);
        if (updatedParametre != null) {
            return ResponseEntity.ok(updatedParametre);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParametre(@PathVariable int id) {
        if (parametreService.deleteParametre(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
