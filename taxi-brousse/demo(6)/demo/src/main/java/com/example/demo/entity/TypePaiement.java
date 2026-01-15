package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypePaiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTypePaiement;

    public Integer getIdTypePaiement() {
        return idTypePaiement;
    }

    public void setIdTypePaiement(Integer idTypePaiement) {
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
