package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Voiture")
public class Voiture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVoiture;

    private String immatriculation;

    public int getNbplace() {
        return nbplace;
    }

    public void setNbplace(int nbplace) {
        this.nbplace = nbplace;
    }

    public int getIdVoiture() {
        return idVoiture;
    }

    public void setIdVoiture(int idVoiture) {
        this.idVoiture = idVoiture;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public int getNoteetat() {
        return noteetat;
    }

    public void setNoteetat(int noteetat) {
        this.noteetat = noteetat;
    }

    public Date getDatearivee() {
        return datearivee;
    }

    public void setDatearivee(Date datearivee) {
        this.datearivee = datearivee;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    private int nbplace;
    private int noteetat;
    private Date datearivee;

    @ManyToOne
    @JoinColumn(name = "id_etat")
    private Etat etat;

    @ManyToOne
    @JoinColumn(name = "id_marque")
    private Marque marque;
}
