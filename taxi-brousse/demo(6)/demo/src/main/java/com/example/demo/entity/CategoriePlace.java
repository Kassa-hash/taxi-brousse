package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriePlace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCatPlace;

    private String libelle;
}
