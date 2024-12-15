package com.youcode.citronix.entity;

import com.youcode.citronix.entity.enums.Saison;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "recoltes")
@Data
public class Recolte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private Saison saison;
    @Column(nullable = false)
    private LocalDate dateRecolte;

    @OneToMany(mappedBy = "recolte", cascade = CascadeType.ALL)
    private List<Vente> ventes;

    @OneToMany(mappedBy = "recolte", cascade = CascadeType.ALL)
    private List<RecoltDetail> recoltDetails;
}

