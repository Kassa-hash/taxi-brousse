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

    private Integer nbplaces;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "Id_Voyage", nullable = false)
    private Voyage voyage;

    @ManyToOne
    @JoinColumn(name = "Id_Client", nullable = false)
    private Client client;
}
