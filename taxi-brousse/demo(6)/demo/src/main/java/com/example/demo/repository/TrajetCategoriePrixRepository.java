package com.example.demo.repository;

import com.example.demo.entity.TrajetCategoriePrix;
import com.example.demo.entity.Voyage;
import com.example.demo.entity.CategoriePlace;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface TrajetCategoriePrixRepository extends JpaRepository<TrajetCategoriePrix, Integer> {
    // Récupère le prix spécifique pour un voyage et une catégorie donnée
    Optional<TrajetCategoriePrix> findByVoyageAndCategoriePlace(Voyage voyage, CategoriePlace cat);

    // Liste tous les prix pour un voyage spécifique
    List<TrajetCategoriePrix> findByVoyage(Voyage voyage);
}