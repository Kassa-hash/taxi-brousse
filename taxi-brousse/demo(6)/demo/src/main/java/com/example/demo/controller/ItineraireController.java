package com.example.demo.controller;

import com.example.demo.entity.Itineraire;
import com.example.demo.service.ItineraireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/itineraires")
@CrossOrigin(origins = "*")
public class ItineraireController {

    @Autowired
    private ItineraireService itineraireService;

    @GetMapping
    public ResponseEntity<List<Itineraire>> getAllItineraires() {
        List<Itineraire> itineraires = itineraireService.getAllItineraires();
        return ResponseEntity.ok(itineraires);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Itineraire> getItineraireById(@PathVariable int id) {
        Optional<Itineraire> itineraire = itineraireService.getItineraireById(id);
        return itineraire.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Itineraire> createItineraire(@RequestBody Itineraire itineraire) {
        Itineraire savedItineraire = itineraireService.saveItineraire(itineraire);
        return ResponseEntity.ok(savedItineraire);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Itineraire> updateItineraire(@PathVariable int id, @RequestBody Itineraire itineraire) {
        Itineraire updatedItineraire = itineraireService.updateItineraire(id, itineraire);
        if (updatedItineraire != null) {
            return ResponseEntity.ok(updatedItineraire);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItineraire(@PathVariable int id) {
        if (itineraireService.deleteItineraire(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
