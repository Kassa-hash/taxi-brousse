package com.example.demo.service;

import com.example.demo.entity.Entretient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntretientRepository extends JpaRepository<Entretient, Integer> {
}
