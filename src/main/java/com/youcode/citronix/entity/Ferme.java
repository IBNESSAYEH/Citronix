package com.youcode.citronix.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity(name = "fermes")
@Data
public class Ferme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String localisation;

    @Column(nullable = false)
    private Double superficie;

    @Column(nullable = true)
    private Double SuperficieExploitee;

    @Column(nullable = false)
    private LocalDate dateCreation;

    @Column(nullable = false)
    private int nombreChamp;

    @OneToMany(mappedBy = "ferme", cascade = CascadeType.ALL)
    private List<Champ> champs;

    @PrePersist
    public void prePersist(){
        dateCreation = LocalDate.now();
        SuperficieExploitee = 0.0;
        nombreChamp = 10;
    }

}
