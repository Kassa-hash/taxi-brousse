package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CategorieClient {
    public int getIdCategorieClient() {
        return idCategorieClient;
    }

    public void setIdCategorieClient(int idCategorieClient) {
        this.idCategorieClient = idCategorieClient;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategorieClient;
    public String libelle;
}
