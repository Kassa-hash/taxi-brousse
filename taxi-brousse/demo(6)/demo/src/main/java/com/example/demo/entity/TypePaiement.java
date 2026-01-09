package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "type_paiement")
public class TypePaiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTypePaiement;

    public int getIdTypePaiement() {
        return idTypePaiement;
    }

    public void setIdTypePaiement(int idTypePaiement) {
        this.idTypePaiement = idTypePaiement;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    private String libelle;
}
