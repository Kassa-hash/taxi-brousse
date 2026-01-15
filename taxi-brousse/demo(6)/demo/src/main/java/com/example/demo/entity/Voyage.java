package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Voyage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVoyage;

    private Integer prix;

    public String getNbPlaceDisponible() {
        return nbPlaceDisponible;
    }

    public void setNbPlaceDisponible(String nbPlaceDisponible) {
        this.nbPlaceDisponible = nbPlaceDisponible;
    }

    public Integer getIdVoyage() {
        return idVoyage;
    }

    public void setIdVoyage(Integer idVoyage) {
        this.idVoyage = idVoyage;
    }

    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    public LocalDate getDatedepart() {
        return datedepart;
    }

    public void setDatedepart(LocalDate datedepart) {
        this.datedepart = datedepart;
    }

    public LocalTime getHeuredepart() {
        return heuredepart;
    }

    public void setHeuredepart(LocalTime heuredepart) {
        this.heuredepart = heuredepart;
    }

    public GareRoutiere getGareDepart() {
        return gareDepart;
    }

    public void setGareDepart(GareRoutiere gareDepart) {
        this.gareDepart = gareDepart;
    }

    public GareRoutiere getGareArrivee() {
        return gareArrivee;
    }

    public void setGareArrivee(GareRoutiere gareArrivee) {
        this.gareArrivee = gareArrivee;
    }

    private String nbPlaceDisponible;
    private LocalDate datedepart;
    private LocalTime heuredepart;

    @ManyToOne
    @JoinColumn(name = "Id_GareRoutiere", nullable = false)
    private GareRoutiere gareDepart;

    @ManyToOne
    @JoinColumn(name = "Id_GareRoutiere_1", nullable = false)
    private GareRoutiere gareArrivee;
}
