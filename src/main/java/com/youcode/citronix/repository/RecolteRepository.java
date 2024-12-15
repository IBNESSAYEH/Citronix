package com.youcode.citronix.repository;


import com.youcode.citronix.entity.Recolte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecolteRepository extends JpaRepository<Recolte, Long> {
//    @Query("Select rd.quantite From recoltes r join recolt_datails  rd in r.id = rd.recolt.id  ")
//    Double getRecolteQuantite(@Param("id") Long id);
}
