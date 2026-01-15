package com.example.demo.controller;

import com.example.demo.entity.CategoriePlace;
import com.example.demo.service.CategoriePlaceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoriePlaces")
public class CategoriePlaceController {
    private final CategoriePlaceService service;

    public CategoriePlaceController(CategoriePlaceService service) {
        this.service = service;
    }

    @GetMapping
    public List<CategoriePlace> getAll() {
        return service.getAll();
    }

    @PostMapping
    public CategoriePlace create(@RequestBody CategoriePlace cat) {
        return service.save(cat);
    }
}
