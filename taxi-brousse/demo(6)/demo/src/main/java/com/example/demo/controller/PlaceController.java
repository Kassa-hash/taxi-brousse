package com.example.demo.controller;

import com.example.demo.entity.Place;
import com.example.demo.service.PlaceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/places")
public class PlaceController {
    private final PlaceService service;

    public PlaceController(PlaceService service) {
        this.service = service;
    }

    @GetMapping
    public List<Place> getAll() {
        return service.getAllPlaces();
    }

    @PostMapping
    public Place create(@RequestBody Place place) {
        return service.savePlace(place);
    }

    @GetMapping("/{id}")
    public Place getById(@PathVariable Integer id) {
        return service.getPlaceById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.deletePlace(id);
    }
}
