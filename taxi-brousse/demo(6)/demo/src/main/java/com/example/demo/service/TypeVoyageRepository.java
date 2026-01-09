package com.example.demo.service;

import com.example.demo.entity.TypeVoyage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeVoyageRepository extends JpaRepository<TypeVoyage, Integer> {
}
