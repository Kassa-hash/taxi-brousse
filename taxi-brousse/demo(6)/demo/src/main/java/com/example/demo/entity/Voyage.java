package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.sql.Time;
import java.util.Date;

@Entity
public class Voyage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVoyage;

    private Date daty;

    public Time getHeuredepart() {
        return heuredepart;
    }

    public void setHeuredepart(Time heuredepart) {
        this.heuredepart = heuredepart;
    }

    public int getIdVoyage() {
        return idVoyage;
    }

    public void setIdVoyage(int idVoyage) {
        this.idVoyage = idVoyage;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public ModeleVoyage getModeleVoyage() {
        return modeleVoyage;
    }

    public void setModeleVoyage(ModeleVoyage modeleVoyage) {
        this.modeleVoyage = modeleVoyage;
    }

    public Voiture getVoiture() {
        return voiture;
    }

    public void setVoiture(Voiture voiture) {
        this.voiture = voiture;
    }

    public Employe getChauffeur() {
        return chauffeur;
    }

    public void setChauffeur(Employe chauffeur) {
        this.chauffeur = chauffeur;
    }

    public Employe getAide_chauffeur() {
        return aide_chauffeur;
    }

    public void setAide_chauffeur(Employe aide_chauffeur) {
        this.aide_chauffeur = aide_chauffeur;
    }

    private Time heuredepart;

    @ManyToOne
    private ModeleVoyage modeleVoyage;

    @ManyToOne
    private Voiture voiture;

    @ManyToOne
    private Employe chauffeur;

    @ManyToOne
    private Employe aide_chauffeur;
}
