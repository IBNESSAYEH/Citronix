package com.youcode.citronix.repository;

import com.youcode.citronix.entity.Ferme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface FermeRepository extends JpaRepository<Ferme, Long>, JpaSpecificationExecutor<Ferme> {
    List<Ferme> findByNom(String nom);
    List<Ferme> findByLocalisation(String localisation);
    List<Ferme> findBySuperficie(Double superficie);
    List<Ferme> findByNomAndLocalisation(String nom, String localisation);
    List<Ferme> findByNomAndLocalisationAndSuperficie(String nom, String localisation, Double superficie);
}
