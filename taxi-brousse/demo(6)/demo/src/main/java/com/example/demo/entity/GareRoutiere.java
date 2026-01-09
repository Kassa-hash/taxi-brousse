package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GareRoutiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idGareRoutiere;

    private String nom;
}
