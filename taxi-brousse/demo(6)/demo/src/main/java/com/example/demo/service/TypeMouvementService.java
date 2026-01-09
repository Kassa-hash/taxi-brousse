package com.example.demo.service;

import com.example.demo.entity.TypeMouvement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeMouvementService {

    @Autowired
    private TypeMouvementRepository typeMouvementRepository;

    public List<TypeMouvement> getAllTypeMouvements() {
        return typeMouvementRepository.findAll();
    }

    public Optional<TypeMouvement> getTypeMouvementById(int id) {
        return typeMouvementRepository.findById(id);
    }

    public TypeMouvement saveTypeMouvement(TypeMouvement typeMouvement) {
        return typeMouvementRepository.save(typeMouvement);
    }

    public TypeMouvement updateTypeMouvement(int id, TypeMouvement typeMouvement) {
        Optional<TypeMouvement> existingTypeMouvement = typeMouvementRepository.findById(id);
        if (existingTypeMouvement.isPresent()) {
            typeMouvement.setIdTypeMouvement(id);
            return typeMouvementRepository.save(typeMouvement);
        }
        return null;
    }

    public boolean deleteTypeMouvement(int id) {
        if (typeMouvementRepository.existsById(id)) {
            typeMouvementRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
