package com.example.demo.service;

import com.example.demo.entity.TypePaiement;
import com.example.demo.repository.TypePaiementRepository;
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

    public Optional<TypePaiement> getTypePaiementById(Integer id) {
        return typePaiementRepository.findById(id);
    }

    public TypePaiement createTypePaiement(TypePaiement typePaiement) {
        return typePaiementRepository.save(typePaiement);
    }

    public TypePaiement updateTypePaiement(Integer id, TypePaiement typePaiement) {
        if (typePaiementRepository.existsById(id)) {
            typePaiement.setIdTypePaiement(id);
            return typePaiementRepository.save(typePaiement);
        }
        return null;
    }

    public void deleteTypePaiement(Integer id) {
        typePaiementRepository.deleteById(id);
    }
}
