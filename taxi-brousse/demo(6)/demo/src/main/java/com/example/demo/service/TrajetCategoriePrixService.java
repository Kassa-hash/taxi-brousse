package com.example.demo.service;

import com.example.demo.entity.TrajetCategoriePrix;
import com.example.demo.repository.TrajetCategoriePrixRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TrajetCategoriePrixService {
    private final TrajetCategoriePrixRepository repository;

    public TrajetCategoriePrixService(TrajetCategoriePrixRepository repository) {
        this.repository = repository;
    }

    public List<TrajetCategoriePrix> getAll() {
        return repository.findAll();
    }

    public TrajetCategoriePrix save(TrajetCategoriePrix tcp) {
        return repository.save(tcp);
    }
}
