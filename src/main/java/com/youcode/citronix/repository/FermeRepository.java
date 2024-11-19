package com.youcode.citronix.repository;

import com.youcode.citronix.entity.Ferme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface FermeRepository extends JpaRepository<Ferme, Long> {

}
