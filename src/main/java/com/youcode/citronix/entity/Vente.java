package com.youcode.citronix.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity(name = "ventes")
@Data
public class Vente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private LocalDate dateVente;
    @Column(nullable = false)
    private Double prixUnitaire;
    @Column(nullable = false)
    private Double revenue;

    @ManyToOne
    @JoinColumn(name = "recolte_id", nullable = false)
    private Recolte recolte;

    private String client;
}

