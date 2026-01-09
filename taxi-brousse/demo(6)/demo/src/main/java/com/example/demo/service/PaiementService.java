package com.example.demo.service;

import com.example.demo.entity.Paiement;
import com.example.demo.repository.PaiementRepository;
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

    public Optional<Paiement> getPaiementById(Integer id) {
        return paiementRepository.findById(id);
    }

    public Paiement createPaiement(Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    public Paiement updatePaiement(Integer id, Paiement paiement) {
        if (paiementRepository.existsById(id)) {
            paiement.setIdPaiement(id);
            return paiementRepository.save(paiement);
        }
        return null;
    }

    public void deletePaiement(Integer id) {
        paiementRepository.deleteById(id);
    }
}
