package com.example.demo.service;

import com.example.demo.entity.Voyage;
import com.example.demo.repository.VoyageRepository;
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
}
