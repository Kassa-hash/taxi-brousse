package com.example.demo.service;

import com.example.demo.entity.Entretient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntretientService {

    @Autowired
    private EntretientRepository entretientRepository;

    public List<Entretient> getAllEntretients() {
        return entretientRepository.findAll();
    }

    public Optional<Entretient> getEntretientById(int id) {
        return entretientRepository.findById(id);
    }

    public Entretient saveEntretient(Entretient entretient) {
        return entretientRepository.save(entretient);
    }

    public Entretient updateEntretient(int id, Entretient entretient) {
        Optional<Entretient> existingEntretient = entretientRepository.findById(id);
        if (existingEntretient.isPresent()) {
            entretient.setIdEntretient(id);
            return entretientRepository.save(entretient);
        }
        return null;
    }

    public boolean deleteEntretient(int id) {
        if (entretientRepository.existsById(id)) {
            entretientRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
