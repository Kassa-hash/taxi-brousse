package com.example.demo.service;

import com.example.demo.entity.TypeMouvement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeMouvementRepository extends JpaRepository<TypeMouvement, Integer> {
}
