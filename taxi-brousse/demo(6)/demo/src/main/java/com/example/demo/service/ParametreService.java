package com.example.demo.service;

import com.example.demo.entity.Parametre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParametreService {

    @Autowired
    private ParametreRepository parametreRepository;

    public List<Parametre> getAllParametres() {
        return parametreRepository.findAll();
    }

    public Optional<Parametre> getParametreById(int id) {
        return parametreRepository.findById(id);
    }

    public Parametre saveParametre(Parametre parametre) {
        return parametreRepository.save(parametre);
    }

    public Parametre updateParametre(int id, Parametre parametre) {
        Optional<Parametre> existingParametre = parametreRepository.findById(id);
        if (existingParametre.isPresent()) {
            parametre.setId(id);
            return parametreRepository.save(parametre);
        }
        return null;
    }

    public boolean deleteParametre(int id) {
        if (parametreRepository.existsById(id)) {
            parametreRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
