package com.example.demo.controller;

import com.example.demo.entity.Achat;
import com.example.demo.service.AchatService;
import com.example.demo.service.ClientService;
import com.example.demo.service.VoyageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/achats")
public class AchatController {
    @Autowired
    private AchatService achatService;

    @Autowired
    private VoyageService voyageService;

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Achat>> getAllAchats() {
        List<Achat> achats = achatService.getAllAchats();
        return ResponseEntity.ok(achats);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getAchatById(@PathVariable Integer id) {
        Optional<Achat> achat = achatService.getAchatById(id);
        if (achat.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            response.put("achat", achat.get());
            response.put("voyage", achat.get().getVoyage());
            response.put("client", achat.get().getClient());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/client/{idClient}")
    public ResponseEntity<List<Achat>> getAchatsByClient(@PathVariable Integer idClient) {
        List<Achat> achats = achatService.getAllAchats();
        achats.removeIf(a -> !a.getClient().getIdClient().equals(idClient));
        return ResponseEntity.ok(achats);
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
