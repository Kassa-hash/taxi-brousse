package com.example.demo.service;

import com.example.demo.entity.Voyage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoyageService {

    @Autowired
    private VoyageRepository voyageRepository;

    public List<Voyage> getAllVoyages() {
        return voyageRepository.findAll();
    }

    public Optional<Voyage> getVoyageById(int id) {
        return voyageRepository.findById(id);
    }

    public Voyage saveVoyage(Voyage voyage) {
        return voyageRepository.save(voyage);
    }

    public Voyage updateVoyage(int id, Voyage voyage) {
        Optional<Voyage> existingVoyage = voyageRepository.findById(id);
        if (existingVoyage.isPresent()) {
            voyage.setIdVoyage(id);
            return voyageRepository.save(voyage);
        }
        return null;
    }

    public boolean deleteVoyage(int id) {
        if (voyageRepository.existsById(id)) {
            voyageRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
