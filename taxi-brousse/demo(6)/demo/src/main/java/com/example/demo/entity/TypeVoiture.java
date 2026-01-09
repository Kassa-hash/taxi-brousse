package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class TypeVoiture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTypeVoiture;

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Integer getIdTypeVoiture() {
        return idTypeVoiture;
    }

    public void setIdTypeVoiture(Integer idTypeVoiture) {
        this.idTypeVoiture = idTypeVoiture;
    }

    @Column(length = 50)
    private String libelle;
}
