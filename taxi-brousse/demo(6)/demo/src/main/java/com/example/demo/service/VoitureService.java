package com.example.demo.service;

import com.example.demo.entity.Voiture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoitureService {

    @Autowired
    private VoitureRepository voitureRepository;

    public List<Voiture> getAllVoitures() {
        return voitureRepository.findAll();
    }

    public Optional<Voiture> getVoitureById(int id) {
        return voitureRepository.findById(id);
    }

    public Voiture saveVoiture(Voiture voiture) {
        return voitureRepository.save(voiture);
    }

    public Voiture updateVoiture(int id, Voiture voiture) {
        Optional<Voiture> existingVoiture = voitureRepository.findById(id);
        if (existingVoiture.isPresent()) {
            voiture.setIdVoiture(id);
            return voitureRepository.save(voiture);
        }
        return null;
    }

    public boolean deleteVoiture(int id) {
        if (voitureRepository.existsById(id)) {
            voitureRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
