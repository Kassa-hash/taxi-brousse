package com.example.demo.service;

import com.example.demo.entity.Achat;
import com.example.demo.repository.AchatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AchatService {
    @Autowired
    private AchatRepository achatRepository;

    public List<Achat> getAllAchats() {
        return achatRepository.findAll();
    }

    public Optional<Achat> getAchatById(Integer id) {
        return achatRepository.findById(id);
    }

    public Achat createAchat(Achat achat) {
        return achatRepository.save(achat);
    }

    public Achat updateAchat(Integer id, Achat achat) {
        if (achatRepository.existsById(id)) {
            achat.setIdAchat(id);
            return achatRepository.save(achat);
        }
        return null;
    }

    public void deleteAchat(Integer id) {
        achatRepository.deleteById(id);
    }
}
