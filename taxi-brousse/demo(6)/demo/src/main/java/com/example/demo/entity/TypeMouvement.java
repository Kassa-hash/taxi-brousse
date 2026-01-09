package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class TypeMouvement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTypeMouvement;

    public Integer getIdTypeMouvement() {
        return idTypeMouvement;
    }

    public void setIdTypeMouvement(Integer idTypeMouvement) {
        this.idTypeMouvement = idTypeMouvement;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Column(length = 50)
    private String libelle;
}
