package com.example.demo.controller;

import com.example.demo.entity.Voyage;
import com.example.demo.repository.VoyageRepository;
import com.example.demo.service.VoyageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/stats")
public class StatistiqueController {
    @Autowired
    private VoyageService voyageService;
    @Autowired private VoyageRepository voyageRepo;

    @GetMapping("/valeur-max/{idVoyage}")
    public ResponseEntity<Map<String, Object>> getValeurMaximale(@PathVariable Integer idVoyage) {
        Voyage voyage = voyageRepo.findById(idVoyage)
            .orElseThrow(() -> new RuntimeException("Voyage non trouvé"));
        
        double valeurMax = voyageService.calculerValeurMaximale(voyage);
        
        Map<String, Object> response = new HashMap<>();
        response.put("voyage", voyage.getIdVoyage());
        response.put("voiture", voyage.getVoiture().getIdVoiture());
        response.put("valeurMaximale", valeurMax);
        
        return ResponseEntity.ok(response);
    }
}
