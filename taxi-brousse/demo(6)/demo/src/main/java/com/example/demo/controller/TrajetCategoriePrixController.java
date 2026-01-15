package com.example.demo.controller;

import com.example.demo.entity.TrajetCategoriePrix;
import com.example.demo.service.TrajetCategoriePrixService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trajetPrix")
public class TrajetCategoriePrixController {
    private final TrajetCategoriePrixService service;

    public TrajetCategoriePrixController(TrajetCategoriePrixService service) {
        this.service = service;
    }

    @GetMapping
    public List<TrajetCategoriePrix> getAll() {
        return service.getAll();
    }

    @PostMapping
    public TrajetCategoriePrix create(@RequestBody TrajetCategoriePrix tcp) {
        return service.save(tcp);
    }
}
