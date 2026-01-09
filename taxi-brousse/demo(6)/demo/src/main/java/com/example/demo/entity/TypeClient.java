package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "typecllient")
public class TypeClient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTypeClient;

    public int getIdTypeClient() {
        return idTypeClient;
    }

    public void setIdTypeClient(int idTypeClient) {
        this.idTypeClient = idTypeClient;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String libelle;
}
