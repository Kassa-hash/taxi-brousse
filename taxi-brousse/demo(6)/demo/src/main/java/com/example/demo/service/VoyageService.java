package com.example.demo.service;

import com.example.demo.entity.Place;
import com.example.demo.entity.TrajetCategoriePrix;
import com.example.demo.entity.Voyage;
import com.example.demo.repository.PlaceRepository;
import com.example.demo.repository.TrajetCategoriePrixRepository;
import com.example.demo.repository.VoyageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VoyageService {
    @Autowired
    private VoyageRepository voyageRepository;

    @Autowired private PlaceRepository placeRepository;
    @Autowired private TrajetCategoriePrixRepository trajetPrixRepo;

    public List<Voyage> getAllVoyages() {
        return voyageRepository.findAll();
    }

    public Optional<Voyage> getVoyageById(Integer id) {
        return voyageRepository.findById(id);
    }

    public Voyage createVoyage(Voyage voyage) {
        return voyageRepository.save(voyage);
    }

    public Voyage updateVoyage(Integer id, Voyage voyage) {
        if (voyageRepository.existsById(id)) {
            voyage.setIdVoyage(id);
            return voyageRepository.save(voyage);
        }
        return null;
    }

    public void deleteVoyage(Integer id) {
        voyageRepository.deleteById(id);
    }

    public double calculerValeurMaximale(Voyage voyage) {
        // 1. Récupérer toutes les places de la voiture assignée au voyage
        List<Place> toutesLesPlaces = placeRepository.findByVoiture(voyage.getVoiture());
        
        double totalVoyage = 0;

        for (Place place : toutesLesPlaces) {
            // 2. Trouver le prix correspondant à la catégorie de cette place pour CE voyage
            Optional<TrajetCategoriePrix> prixConfig = trajetPrixRepo
                .findByVoyageAndCategoriePlace(voyage, place.getCategoriePlace());
            
            if (prixConfig.isPresent()) {
                totalVoyage += prixConfig.get().getPrix();
            }
        }
        return totalVoyage;
    }
}
