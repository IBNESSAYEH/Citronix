package com.youcode.citronix.entity;

import com.youcode.citronix.entity.enums.ArbreAge;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "arbres")
@Data
public class Arbre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private ArbreAge age;
    @Column(nullable = false)
    private LocalDate dateDePlantation;
    @ManyToOne
    @JoinColumn(name = "champ_id", nullable = false)
    private Champ champ;

    @OneToMany(mappedBy = "arbre", cascade = CascadeType.ALL)
    private List<RecoltDetail> recoltDetails;


}
