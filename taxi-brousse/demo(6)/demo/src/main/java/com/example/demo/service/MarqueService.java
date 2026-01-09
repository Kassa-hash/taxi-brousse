package com.example.demo.service;

import com.example.demo.entity.Marque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarqueService {

    @Autowired
    private MarqueRepository marqueRepository;

    public List<Marque> getAllMarques() {
        return marqueRepository.findAll();
    }

    public Optional<Marque> getMarqueById(int id) {
        return marqueRepository.findById(id);
    }

    public Marque saveMarque(Marque marque) {
        return marqueRepository.save(marque);
    }

    public Marque updateMarque(int id, Marque marque) {
        Optional<Marque> existingMarque = marqueRepository.findById(id);
        if (existingMarque.isPresent()) {
            marque.setId(id);
            return marqueRepository.save(marque);
        }
        return null;
    }

    public boolean deleteMarque(int id) {
        if (marqueRepository.existsById(id)) {
            marqueRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
