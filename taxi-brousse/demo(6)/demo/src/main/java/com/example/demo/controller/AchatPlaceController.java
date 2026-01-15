package com.example.demo.controller;

import com.example.demo.entity.AchatPlace;
import com.example.demo.service.AchatPlaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/achatPlace")
public class AchatPlaceController {

    private final AchatPlaceService achatPlaceService;

    public AchatPlaceController(AchatPlaceService achatPlaceService) {
        this.achatPlaceService = achatPlaceService;
    }

    @GetMapping
    public List<AchatPlace> getAll() {
        return achatPlaceService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AchatPlace> getById(@PathVariable Integer id) {
        return achatPlaceService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public AchatPlace create(@RequestBody AchatPlace achatPlace) {
        return achatPlaceService.save(achatPlace);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (achatPlaceService.getById(id).isPresent()) {
            achatPlaceService.delete(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
