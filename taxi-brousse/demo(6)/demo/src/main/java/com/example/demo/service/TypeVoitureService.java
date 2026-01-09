package com.example.demo.service;

import com.example.demo.entity.TypeVoiture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeVoitureService {

    @Autowired
    private TypeVoitureRepository typeVoitureRepository;

    public List<TypeVoiture> getAllTypeVoitures() {
        return typeVoitureRepository.findAll();
    }

    public Optional<TypeVoiture> getTypeVoitureById(int id) {
        return typeVoitureRepository.findById(id);
    }

    public TypeVoiture saveTypeVoiture(TypeVoiture typeVoiture) {
        return typeVoitureRepository.save(typeVoiture);
    }

    public TypeVoiture updateTypeVoiture(int id, TypeVoiture typeVoiture) {
        Optional<TypeVoiture> existingTypeVoiture = typeVoitureRepository.findById(id);
        if (existingTypeVoiture.isPresent()) {
            typeVoiture.setIdTypeVoiture(id);
            return typeVoitureRepository.save(typeVoiture);
        }
        return null;
    }

    public boolean deleteTypeVoiture(int id) {
        if (typeVoitureRepository.existsById(id)) {
            typeVoitureRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
