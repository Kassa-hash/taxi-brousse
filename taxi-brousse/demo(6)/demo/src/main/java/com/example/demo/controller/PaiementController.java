package com.example.demo.controller;

import com.example.demo.entity.Paiement;
import com.example.demo.service.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/paiements")
@CrossOrigin(origins = "*")
public class PaiementController {

    @Autowired
    private PaiementService paiementService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Paiement>> getAllPaiements() {
        List<Paiement> paiements = paiementService.getAllPaiements();
        return ResponseEntity.ok(paiements);
    }

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String viewPaiements(Model model) {
        model.addAttribute("paiements", paiementService.getAllPaiements());
        return "paiements/list";
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Paiement> getPaiementById(@PathVariable int id) {
        Optional<Paiement> paiement = paiementService.getPaiementById(id);
        return paiement.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Paiement> createPaiement(@RequestBody Paiement paiement) {
        Paiement savedPaiement = paiementService.savePaiement(paiement);
        return ResponseEntity.ok(savedPaiement);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Paiement> updatePaiement(@PathVariable int id, @RequestBody Paiement paiement) {
        Paiement updatedPaiement = paiementService.updatePaiement(id, paiement);
        if (updatedPaiement != null) {
            return ResponseEntity.ok(updatedPaiement);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Void> deletePaiement(@PathVariable int id) {
        if (paiementService.deletePaiement(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
