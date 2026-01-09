package com.example.demo.controller;

import com.example.demo.entity.CategorieClient;
import com.example.demo.service.CategorieClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorie-clients")
@CrossOrigin(origins = "*")
public class CategorieClientController {

    @Autowired
    private CategorieClientService categorieClientService;

    @GetMapping
    public ResponseEntity<List<CategorieClient>> getAllCategorieClients() {
        List<CategorieClient> categorieClients = categorieClientService.getAllCategorieClients();
        return ResponseEntity.ok(categorieClients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategorieClient> getCategorieClientById(@PathVariable int id) {
        Optional<CategorieClient> categorieClient = categorieClientService.getCategorieClientById(id);
        return categorieClient.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CategorieClient> createCategorieClient(@RequestBody CategorieClient categorieClient) {
        CategorieClient savedCategorieClient = categorieClientService.saveCategorieClient(categorieClient);
        return ResponseEntity.ok(savedCategorieClient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategorieClient> updateCategorieClient(@PathVariable int id, @RequestBody CategorieClient categorieClient) {
        CategorieClient updatedCategorieClient = categorieClientService.updateCategorieClient(id, categorieClient);
        if (updatedCategorieClient != null) {
            return ResponseEntity.ok(updatedCategorieClient);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategorieClient(@PathVariable int id) {
        if (categorieClientService.deleteCategorieClient(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
