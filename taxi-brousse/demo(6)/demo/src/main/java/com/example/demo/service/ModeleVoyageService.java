package com.example.demo.service;

import com.example.demo.entity.ModeleVoyage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModeleVoyageService {

    @Autowired
    private ModeleVoyageRepository modeleVoyageRepository;

    public List<ModeleVoyage> getAllModeleVoyages() {
        return modeleVoyageRepository.findAll();
    }

    public Optional<ModeleVoyage> getModeleVoyageById(int id) {
        return modeleVoyageRepository.findById(id);
    }

    public ModeleVoyage saveModeleVoyage(ModeleVoyage modeleVoyage) {
        return modeleVoyageRepository.save(modeleVoyage);
    }

    public ModeleVoyage updateModeleVoyage(int id, ModeleVoyage modeleVoyage) {
        Optional<ModeleVoyage> existingModeleVoyage = modeleVoyageRepository.findById(id);
        if (existingModeleVoyage.isPresent()) {
            modeleVoyage.setIdModeleVoyage(id);
            return modeleVoyageRepository.save(modeleVoyage);
        }
        return null;
    }

    public boolean deleteModeleVoyage(int id) {
        if (modeleVoyageRepository.existsById(id)) {
            modeleVoyageRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
