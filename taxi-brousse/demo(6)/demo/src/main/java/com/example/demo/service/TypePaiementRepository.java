package com.example.demo.service;

import com.example.demo.entity.TypePaiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypePaiementRepository extends JpaRepository<TypePaiement, Integer> {
}
