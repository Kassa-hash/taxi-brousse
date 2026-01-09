package com.example.demo.service;

import com.example.demo.entity.CategorieClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieClientService {

    @Autowired
    private CategorieClientRepository categorieClientRepository;

    public List<CategorieClient> getAllCategorieClients() {
        return categorieClientRepository.findAll();
    }

    public Optional<CategorieClient> getCategorieClientById(int id) {
        return categorieClientRepository.findById(id);
    }

    public CategorieClient saveCategorieClient(CategorieClient categorieClient) {
        return categorieClientRepository.save(categorieClient);
    }

    public CategorieClient updateCategorieClient(int id, CategorieClient categorieClient) {
        Optional<CategorieClient> existingCategorieClient = categorieClientRepository.findById(id);
        if (existingCategorieClient.isPresent()) {
            categorieClient.setIdCategorieClient(id);
            return categorieClientRepository.save(categorieClient);
        }
        return null;
    }

    public boolean deleteCategorieClient(int id) {
        if (categorieClientRepository.existsById(id)) {
            categorieClientRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
