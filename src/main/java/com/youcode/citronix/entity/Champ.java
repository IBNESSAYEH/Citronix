package com.youcode.citronix.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity(name = "champs")
@Data
public class Champ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private Double superficie;
    @Column(nullable = false)
    private Double nbrArbre;

    @ManyToOne
    @JoinColumn(name = "ferme_id", nullable = false)
    private Ferme ferme;

    @OneToMany(mappedBy = "champ", cascade = CascadeType.ALL)
    private List<Arbre> arbres;



}
