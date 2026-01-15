package com.example.demo.service;

import com.example.demo.entity.CategoriePlace;
import com.example.demo.repository.CategoriePlaceRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoriePlaceService {
    private final CategoriePlaceRepository repository;

    public CategoriePlaceService(CategoriePlaceRepository repository) {
        this.repository = repository;
    }

    public List<CategoriePlace> getAll() {
        return repository.findAll();
    }

    public CategoriePlace save(CategoriePlace cat) {
        return repository.save(cat);
    }
}
