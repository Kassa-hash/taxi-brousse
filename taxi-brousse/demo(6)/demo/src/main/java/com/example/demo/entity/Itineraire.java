package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "itineraire")
public class Itineraire {
    public int getIdItineraire() {
        return idItineraire;
    }

    public void setIdItineraire(int idItineraire) {
        this.idItineraire = idItineraire;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getDuree() {
        return duree;
    }

    public void setDuree(double duree) {
        this.duree = duree;
    }

    public Lieu getDepart() {
        return depart;
    }

    public void setDepart(Lieu depart) {
        this.depart = depart;
    }

    public Lieu getArrivee() {
        return arrivee;
    }

    public void setArrivee(Lieu arrivee) {
        this.arrivee = arrivee;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idItineraire;

    private double distance;
    private double duree;

    @ManyToOne
    @JoinColumn(name = "depart")
    private Lieu depart;

    @ManyToOne
    @JoinColumn(name = "arrivee")
    private Lieu arrivee;
}
