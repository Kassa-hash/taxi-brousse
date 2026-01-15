package com.example.demo.service;

import com.example.demo.entity.AchatPlace;
import com.example.demo.repository.AchatPlaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AchatPlaceService {

    private final AchatPlaceRepository achatPlaceRepository;

    public AchatPlaceService(AchatPlaceRepository achatPlaceRepository) {
        this.achatPlaceRepository = achatPlaceRepository;
    }

    public List<AchatPlace> getAll() {
        return achatPlaceRepository.findAll();
    }

    public Optional<AchatPlace> getById(Integer id) {
        return achatPlaceRepository.findById(id);
    }

    public AchatPlace save(AchatPlace achatPlace) {
        return achatPlaceRepository.save(achatPlace);
    }

    public void delete(Integer id) {
        achatPlaceRepository.deleteById(id);
    }
}
