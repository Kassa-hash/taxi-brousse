package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPlace;

    private Integer numero;
    private Boolean statut;

    @ManyToOne
    @JoinColumn(name = "Id_Client")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "Id_Voiture")
    private Voiture voiture;

    public CategoriePlace getCategoriePlace() {
        return categoriePlace;
    }

    public void setCategoriePlace(CategoriePlace categoriePlace) {
        this.categoriePlace = categoriePlace;
    }

    public Voiture getVoiture() {
        return voiture;
    }

    public void setVoiture(Voiture voiture) {
        this.voiture = voiture;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Boolean getStatut() {
        return statut;
    }

    public void setStatut(Boolean statut) {
        this.statut = statut;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(Integer idPlace) {
        this.idPlace = idPlace;
    }

    @ManyToOne
    @JoinColumn(name = "Id_Cat_Place")
    private CategoriePlace categoriePlace;
}
