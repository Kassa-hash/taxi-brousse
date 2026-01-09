package com.example.demo.service;

import com.example.demo.entity.Itineraire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItineraireService {

    @Autowired
    private ItineraireRepository itineraireRepository;

    public List<Itineraire> getAllItineraires() {
        return itineraireRepository.findAll();
    }

    public Optional<Itineraire> getItineraireById(int id) {
        return itineraireRepository.findById(id);
    }

    public Itineraire saveItineraire(Itineraire itineraire) {
        return itineraireRepository.save(itineraire);
    }

    public Itineraire updateItineraire(int id, Itineraire itineraire) {
        Optional<Itineraire> existingItineraire = itineraireRepository.findById(id);
        if (existingItineraire.isPresent()) {
            itineraire.setIdItineraire(id);
            return itineraireRepository.save(itineraire);
        }
        return null;
    }

    public boolean deleteItineraire(int id) {
        if (itineraireRepository.existsById(id)) {
            itineraireRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
