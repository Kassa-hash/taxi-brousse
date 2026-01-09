package com.example.demo.service;

import com.example.demo.entity.Employe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeService {

    @Autowired
    private EmployeRepository employeRepository;

    public List<Employe> getAllEmployes() {
        return employeRepository.findAll();
    }

    public Optional<Employe> getEmployeById(int id) {
        return employeRepository.findById(id);
    }

    public Employe saveEmploye(Employe employe) {
        return employeRepository.save(employe);
    }

    public Employe updateEmploye(int id, Employe employe) {
        Optional<Employe> existingEmploye = employeRepository.findById(id);
        if (existingEmploye.isPresent()) {
            employe.setId(id);
            return employeRepository.save(employe);
        }
        return null;
    }

    public boolean deleteEmploye(int id) {
        if (employeRepository.existsById(id)) {
            employeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
