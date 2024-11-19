package com.youcode.citronix.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity(name = "arbres")
@Data
public class Arbre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate datePlantation;
    @ManyToOne
    @JoinColumn(name = "champ_id", nullable = false)
    private Champ champ;
}
