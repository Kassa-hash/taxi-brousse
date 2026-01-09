package com.example.demo.service;

import com.example.demo.entity.Itineraire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItineraireRepository extends JpaRepository<Itineraire, Integer> {
}
