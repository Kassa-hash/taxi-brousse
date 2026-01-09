package com.example.demo.service;

import com.example.demo.entity.ModeleVoyage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeleVoyageRepository extends JpaRepository<ModeleVoyage, Integer> {
}
