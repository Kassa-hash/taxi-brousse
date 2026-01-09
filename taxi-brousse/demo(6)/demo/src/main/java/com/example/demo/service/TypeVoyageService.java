package com.example.demo.service;

import com.example.demo.entity.TypeVoyage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeVoyageService {

    @Autowired
    private TypeVoyageRepository typeVoyageRepository;

    public List<TypeVoyage> getAllTypeVoyages() {
        return typeVoyageRepository.findAll();
    }

    public Optional<TypeVoyage> getTypeVoyageById(int id) {
        return typeVoyageRepository.findById(id);
    }

    public TypeVoyage saveTypeVoyage(TypeVoyage typeVoyage) {
        return typeVoyageRepository.save(typeVoyage);
    }

    public TypeVoyage updateTypeVoyage(int id, TypeVoyage typeVoyage) {
        Optional<TypeVoyage> existingTypeVoyage = typeVoyageRepository.findById(id);
        if (existingTypeVoyage.isPresent()) {
            typeVoyage.setIdTypeVoyage(id);
            return typeVoyageRepository.save(typeVoyage);
        }
        return null;
    }

    public boolean deleteTypeVoyage(int id) {
        if (typeVoyageRepository.existsById(id)) {
            typeVoyageRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
