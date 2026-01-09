package com.example.demo.service;

import com.example.demo.entity.Statut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatutService {

    @Autowired
    private StatutRepository statutRepository;

    public List<Statut> getAllStatuts() {
        return statutRepository.findAll();
    }

    public Optional<Statut> getStatutById(int id) {
        return statutRepository.findById(id);
    }

    public Statut saveStatut(Statut statut) {
        return statutRepository.save(statut);
    }

    public Statut updateStatut(int id, Statut statut) {
        Optional<Statut> existingStatut = statutRepository.findById(id);
        if (existingStatut.isPresent()) {
            statut.setIdStatut(id);
            return statutRepository.save(statut);
        }
        return null;
    }

    public boolean deleteStatut(int id) {
        if (statutRepository.existsById(id)) {
            statutRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
