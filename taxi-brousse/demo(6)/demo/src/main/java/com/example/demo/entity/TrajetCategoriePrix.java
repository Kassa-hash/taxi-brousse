package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrajetCategoriePrix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "Id_Voyage")
    private Voyage voyage;

    @ManyToOne
    @JoinColumn(name = "Id_Cat_Place")
    private CategoriePlace categoriePlace;

    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    public CategoriePlace getCategoriePlace() {
        return categoriePlace;
    }

    public void setCategoriePlace(CategoriePlace categoriePlace) {
        this.categoriePlace = categoriePlace;
    }

    public Voyage getVoyage() {
        return voyage;
    }

    public void setVoyage(Voyage voyage) {
        this.voyage = voyage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private Integer prix;
}
