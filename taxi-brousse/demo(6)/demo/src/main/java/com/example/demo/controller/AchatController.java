package com.example.demo.controller;

import com.example.demo.entity.Achat;
import com.example.demo.service.AchatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/achats")
public class AchatController {
    @Autowired
    private AchatService achatService;

    @GetMapping
    public ResponseEntity<List<Achat>> getAllAchats() {
        List<Achat> achats = achatService.getAllAchats();
        return ResponseEntity.ok(achats);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Achat> getAchatById(@PathVariable Integer id) {
        Optional<Achat> achat = achatService.getAchatById(id);
        return achat.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Achat> createAchat(@RequestBody Achat achat) {
        Achat createdAchat = achatService.createAchat(achat);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAchat);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Achat> updateAchat(@PathVariable Integer id, @RequestBody Achat achat) {
        Achat updatedAchat = achatService.updateAchat(id, achat);
        if (updatedAchat != null) {
            return ResponseEntity.ok(updatedAchat);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAchat(@PathVariable Integer id) {
        achatService.deleteAchat(id);
        return ResponseEntity.noContent().build();
    }
}
