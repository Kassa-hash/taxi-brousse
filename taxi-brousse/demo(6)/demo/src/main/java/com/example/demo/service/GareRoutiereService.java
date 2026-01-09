package com.example.demo.service;

import com.example.demo.entity.GareRoutiere;
import com.example.demo.repository.GareRoutiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GareRoutiereService {
    @Autowired
    private GareRoutiereRepository gareRoutiereRepository;

    public List<GareRoutiere> getAllGareRoutiere() {
        return gareRoutiereRepository.findAll();
    }

    public Optional<GareRoutiere> getGareRoutiereById(Integer id) {
        return gareRoutiereRepository.findById(id);
    }

    public GareRoutiere createGareRoutiere(GareRoutiere gareRoutiere) {
        return gareRoutiereRepository.save(gareRoutiere);
    }

    public GareRoutiere updateGareRoutiere(Integer id, GareRoutiere gareRoutiere) {
        if (gareRoutiereRepository.existsById(id)) {
            gareRoutiere.setIdGareRoutiere(id);
            return gareRoutiereRepository.save(gareRoutiere);
        }
        return null;
    }

    public void deleteGareRoutiere(Integer id) {
        gareRoutiereRepository.deleteById(id);
    }
}
