package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Caisse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCaisse;

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Integer getIdCaisse() {
        return idCaisse;
    }

    public void setIdCaisse(Integer idCaisse) {
        this.idCaisse = idCaisse;
    }

    @Column(length = 50)
    private String libelle;
}
