package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AchatPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAchatPlace;

    @ManyToOne
    @JoinColumn(name = "idAchat", nullable = false)
    private Achat achat;

    @ManyToOne
    @JoinColumn(name = "idPlace", nullable = false)
    private Place place;
}
