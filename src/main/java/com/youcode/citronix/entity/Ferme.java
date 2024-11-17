package com.youcode.citronix.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "fermes")
@Data
public class Ferme {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String localisation;

    @Column(nullable = false)
    private Double superficie;

    @Column(nullable = false)
    private LocalDate dateCreation;


}
