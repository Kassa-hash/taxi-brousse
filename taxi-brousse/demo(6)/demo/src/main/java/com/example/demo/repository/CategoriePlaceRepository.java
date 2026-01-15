package com.example.demo.repository;

import com.example.demo.entity.CategoriePlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriePlaceRepository extends JpaRepository<CategoriePlace, Integer> {
}
