package com.example.demo.service;

import com.example.demo.entity.Place;
import com.example.demo.repository.PlaceRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlaceService {
    private final PlaceRepository placeRepository;

    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }

    public Place savePlace(Place place) {
        return placeRepository.save(place);
    }

    public Place getPlaceById(Integer id) {
        return placeRepository.findById(id).orElse(null);
    }

    public void deletePlace(Integer id) {
        placeRepository.deleteById(id);
    }
}
