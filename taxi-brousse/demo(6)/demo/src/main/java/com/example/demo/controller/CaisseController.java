package com.example.demo.controller;

import com.example.demo.entity.Caisse;
import com.example.demo.service.CaisseService;
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
@RequestMapping("/api/caisses")
@CrossOrigin(origins = "*")
public class CaisseController {

    @Autowired
    private CaisseService caisseService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Caisse>> getAllCaisses() {
        List<Caisse> caisses = caisseService.getAllCaisses();
        return ResponseEntity.ok(caisses);
    }

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String viewCaisses(Model model) {
        model.addAttribute("caisses", caisseService.getAllCaisses());
        return "caisses/list";
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Caisse> getCaisseById(@PathVariable int id) {
        Optional<Caisse> caisse = caisseService.getCaisseById(id);
        return caisse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Caisse> createCaisse(@RequestBody Caisse caisse) {
        Caisse savedCaisse = caisseService.saveCaisse(caisse);
        return ResponseEntity.ok(savedCaisse);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Caisse> updateCaisse(@PathVariable int id, @RequestBody Caisse caisse) {
        Caisse updatedCaisse = caisseService.updateCaisse(id, caisse);
        if (updatedCaisse != null) {
            return ResponseEntity.ok(updatedCaisse);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Void> deleteCaisse(@PathVariable int id) {
        if (caisseService.deleteCaisse(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
