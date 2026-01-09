package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class ModeleVoyage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idModeleVoyage;

    private int prix;

    public int getIdModeleVoyage() {
        return idModeleVoyage;
    }

    public void setIdModeleVoyage(int idModeleVoyage) {
        this.idModeleVoyage = idModeleVoyage;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public Itineraire getItineraire() {
        return itineraire;
    }

    public void setItineraire(Itineraire itineraire) {
        this.itineraire = itineraire;
    }

    public TypeVoyage getTypeVoyage() {
        return typeVoyage;
    }

    public void setTypeVoyage(TypeVoyage typeVoyage) {
        this.typeVoyage = typeVoyage;
    }

    @ManyToOne
    private Itineraire itineraire;
    @ManyToOne private TypeVoyage typeVoyage;
}
