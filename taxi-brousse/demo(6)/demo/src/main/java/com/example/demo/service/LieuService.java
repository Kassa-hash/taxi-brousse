package com.example.demo.service;

import com.example.demo.entity.Lieu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LieuService {

    @Autowired
    private LieuRepository lieuRepository;

    public List<Lieu> getAllLieux() {
        return lieuRepository.findAll();
    }

    public Optional<Lieu> getLieuById(int id) {
        return lieuRepository.findById(id);
    }

    public Lieu saveLieu(Lieu lieu) {
        return lieuRepository.save(lieu);
    }

    public Lieu updateLieu(int id, Lieu lieu) {
        Optional<Lieu> existingLieu = lieuRepository.findById(id);
        if (existingLieu.isPresent()) {
            lieu.setIdLieu(id);
            return lieuRepository.save(lieu);
        }
        return null;
    }

    public boolean deleteLieu(int id) {
        if (lieuRepository.existsById(id)) {
            lieuRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
