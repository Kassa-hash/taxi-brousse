package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Entretient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEntretient;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public Integer getIdEntretient() {
        return idEntretient;
    }

    public void setIdEntretient(Integer idEntretient) {
        this.idEntretient = idEntretient;
    }

    @Column(length = 50)
    private String date;

    @Column(length = 50)
    private String motif;
}
