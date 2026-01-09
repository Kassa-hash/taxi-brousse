package com.example.demo.service;

import com.example.demo.entity.Paiement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaiementService {

    @Autowired
    private PaiementRepository paiementRepository;

    public List<Paiement> getAllPaiements() {
        return paiementRepository.findAll();
    }

    public Optional<Paiement> getPaiementById(int id) {
        return paiementRepository.findById(id);
    }

    public Paiement savePaiement(Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    public Paiement updatePaiement(int id, Paiement paiement) {
        Optional<Paiement> existingPaiement = paiementRepository.findById(id);
        if (existingPaiement.isPresent()) {
            paiement.setIdPaiement(id);
            return paiementRepository.save(paiement);
        }
        return null;
    }

    public boolean deletePaiement(int id) {
        if (paiementRepository.existsById(id)) {
            paiementRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
