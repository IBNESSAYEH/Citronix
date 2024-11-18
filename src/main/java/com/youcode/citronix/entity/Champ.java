package com.youcode.citronix.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity(name = "champs")
@Data
public class Champ {
    @Id
    @GeneratedValue
    private UUID id;

    private String nom;

    private Double superficie;

    @ManyToOne
    @JoinColumn(name = "ferme_id", nullable = false)
    private Ferme ferme;

}
