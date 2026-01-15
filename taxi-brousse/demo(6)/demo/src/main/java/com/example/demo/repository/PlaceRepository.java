package com.example.demo.repository;

import com.example.demo.entity.Place;
import com.example.demo.entity.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Integer> {
    // Récupère toutes les places d'une voiture pour calculer la valeur totale
    List<Place> findByVoiture(Voiture voiture);
}