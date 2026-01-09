package com.example.demo.service;

import com.example.demo.entity.TypePaiement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypePaiementService {

    @Autowired
    private TypePaiementRepository typePaiementRepository;

    public List<TypePaiement> getAllTypePaiements() {
        return typePaiementRepository.findAll();
    }

    public Optional<TypePaiement> getTypePaiementById(int id) {
        return typePaiementRepository.findById(id);
    }

    public TypePaiement saveTypePaiement(TypePaiement typePaiement) {
        return typePaiementRepository.save(typePaiement);
    }

    public TypePaiement updateTypePaiement(int id, TypePaiement typePaiement) {
        Optional<TypePaiement> existingTypePaiement = typePaiementRepository.findById(id);
        if (existingTypePaiement.isPresent()) {
            typePaiement.setIdTypePaiement(id);
            return typePaiementRepository.save(typePaiement);
        }
        return null;
    }

    public boolean deleteTypePaiement(int id) {
        if (typePaiementRepository.existsById(id)) {
            typePaiementRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
