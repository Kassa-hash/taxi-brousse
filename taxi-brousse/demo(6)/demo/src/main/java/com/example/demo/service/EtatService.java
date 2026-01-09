package com.example.demo.service;

import com.example.demo.entity.Etat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtatService {

    @Autowired
    private EtatRepository etatRepository;

    public List<Etat> getAllEtats() {
        return etatRepository.findAll();
    }

    public Optional<Etat> getEtatById(int id) {
        return etatRepository.findById(id);
    }

    public Etat saveEtat(Etat etat) {
        return etatRepository.save(etat);
    }

    public Etat updateEtat(int id, Etat etat) {
        Optional<Etat> existingEtat = etatRepository.findById(id);
        if (existingEtat.isPresent()) {
            etat.setId(id);
            return etatRepository.save(etat);
        }
        return null;
    }

    public boolean deleteEtat(int id) {
        if (etatRepository.existsById(id)) {
            etatRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
