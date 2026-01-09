package com.example.demo.service;

import com.example.demo.entity.TypeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeClientService {

    @Autowired
    private TypeClientRepository typeClientRepository;

    public List<TypeClient> getAllTypeClients() {
        return typeClientRepository.findAll();
    }

    public Optional<TypeClient> getTypeClientById(int id) {
        return typeClientRepository.findById(id);
    }

    public TypeClient saveTypeClient(TypeClient typeClient) {
        return typeClientRepository.save(typeClient);
    }

    public TypeClient updateTypeClient(int id, TypeClient typeClient) {
        Optional<TypeClient> existingTypeClient = typeClientRepository.findById(id);
        if (existingTypeClient.isPresent()) {
            typeClient.setIdTypeClient(id);
            return typeClientRepository.save(typeClient);
        }
        return null;
    }

    public boolean deleteTypeClient(int id) {
        if (typeClientRepository.existsById(id)) {
            typeClientRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
