package com.youcode.citronix.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "recolt_details")
@Data
public class RecoltDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "recolte_id", nullable = false)
    private Recolte recolte;

    @ManyToOne
    @JoinColumn(name = "arbre_id", nullable = false)
    private Arbre arbre;

    private Double quantite;
}
