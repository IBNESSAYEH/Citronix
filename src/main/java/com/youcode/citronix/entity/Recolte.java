package com.youcode.citronix.entity;

import com.youcode.citronix.entity.enums.Saison;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity(name = "recoltes")
@Data
public class Recolte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Saison saison;

    private LocalDate dateRecolte;

    private Double quantiteTotale;
}

