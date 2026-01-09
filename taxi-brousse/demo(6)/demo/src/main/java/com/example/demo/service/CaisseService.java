package com.example.demo.service;

import com.example.demo.entity.Caisse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CaisseService {

    @Autowired
    private CaisseRepository caisseRepository;

    public List<Caisse> getAllCaisses() {
        return caisseRepository.findAll();
    }

    public Optional<Caisse> getCaisseById(int id) {
        return caisseRepository.findById(id);
    }

    public Caisse saveCaisse(Caisse caisse) {
        return caisseRepository.save(caisse);
    }

    public Caisse updateCaisse(int id, Caisse caisse) {
        Optional<Caisse> existingCaisse = caisseRepository.findById(id);
        if (existingCaisse.isPresent()) {
            caisse.setIdCaisse(id);
            return caisseRepository.save(caisse);
        }
        return null;
    }

    public boolean deleteCaisse(int id) {
        if (caisseRepository.existsById(id)) {
            caisseRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
