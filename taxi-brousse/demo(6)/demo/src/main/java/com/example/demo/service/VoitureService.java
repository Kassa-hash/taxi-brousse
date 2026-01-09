package com.example.demo.service;

import com.example.demo.entity.Voiture;
import com.example.demo.repository.VoitureRepository;
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

    public Optional<Voiture> getVoitureById(Integer id) {
        return voitureRepository.findById(id);
    }

    public Voiture createVoiture(Voiture voiture) {
        return voitureRepository.save(voiture);
    }

    public Voiture updateVoiture(Integer id, Voiture voiture) {
        if (voitureRepository.existsById(id)) {
            voiture.setIdVoiture(id);
            return voitureRepository.save(voiture);
        }
        return null;
    }

    public void deleteVoiture(Integer id) {
        voitureRepository.deleteById(id);
    }
}
