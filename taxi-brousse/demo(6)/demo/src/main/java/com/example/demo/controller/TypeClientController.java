package com.example.demo.controller;

import com.example.demo.entity.TypeClient;
import com.example.demo.service.TypeClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/type-clients")
@CrossOrigin(origins = "*")
public class TypeClientController {

    @Autowired
    private TypeClientService typeClientService;

    @GetMapping
    public ResponseEntity<List<TypeClient>> getAllTypeClients() {
        List<TypeClient> typeClients = typeClientService.getAllTypeClients();
        return ResponseEntity.ok(typeClients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeClient> getTypeClientById(@PathVariable int id) {
        Optional<TypeClient> typeClient = typeClientService.getTypeClientById(id);
        return typeClient.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TypeClient> createTypeClient(@RequestBody TypeClient typeClient) {
        TypeClient savedTypeClient = typeClientService.saveTypeClient(typeClient);
        return ResponseEntity.ok(savedTypeClient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypeClient> updateTypeClient(@PathVariable int id, @RequestBody TypeClient typeClient) {
        TypeClient updatedTypeClient = typeClientService.updateTypeClient(id, typeClient);
        if (updatedTypeClient != null) {
            return ResponseEntity.ok(updatedTypeClient);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTypeClient(@PathVariable int id) {
        if (typeClientService.deleteTypeClient(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
