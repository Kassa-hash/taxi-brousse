package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Achat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAchat;

    public Integer getNbplaces() {
        return nbplaces;
    }

    public void setNbplaces(Integer nbplaces) {
        this.nbplaces = nbplaces;
    }

    public Integer getIdAchat() {
        return idAchat;
    }

    public void setIdAchat(Integer idAchat) {
        this.idAchat = idAchat;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Voyage getVoyage() {
        return voyage;
    }

    public void setVoyage(Voyage voyage) {
        this.voyage = voyage;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    private Integer nbplaces;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "Id_Voyage", nullable = false)
    private Voyage voyage;

    @ManyToOne
    @JoinColumn(name = "Id_Client", nullable = false)
    private Client client;
}
