package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GareRoutiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idGareRoutiere;

    public Integer getIdGareRoutiere() {
        return idGareRoutiere;
    }

    public void setIdGareRoutiere(Integer idGareRoutiere) {
        this.idGareRoutiere = idGareRoutiere;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    private String nom;
}
