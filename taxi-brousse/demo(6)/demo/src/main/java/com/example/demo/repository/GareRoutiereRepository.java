package com.example.demo.repository;

import com.example.demo.entity.GareRoutiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GareRoutiereRepository extends JpaRepository<GareRoutiere, Integer> {
}
