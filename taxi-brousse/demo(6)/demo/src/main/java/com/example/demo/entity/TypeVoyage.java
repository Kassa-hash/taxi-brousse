package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "type_voyage")
public class TypeVoyage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTypeVoyage;

    public int getIdTypeVoyage() {
        return idTypeVoyage;
    }

    public void setIdTypeVoyage(int idTypeVoyage) {
        this.idTypeVoyage = idTypeVoyage;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    private String libelle;
}
