package com.youcode.citronix.repository;


import com.youcode.citronix.entity.Recolte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecolteRepository extends JpaRepository<Recolte, Long> {
}
