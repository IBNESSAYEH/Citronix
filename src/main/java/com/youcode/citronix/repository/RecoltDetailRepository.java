package com.youcode.citronix.repository;

import com.youcode.citronix.entity.RecoltDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecoltDetailRepository extends JpaRepository<RecoltDetail, Long> {
    @Query("SELECT r FROM recolt_details r WHERE r.arbre.id = :arbreId ORDER BY r.id DESC")
    Optional<RecoltDetail> findLastByArbreId(@Param("arbreId") Long arbreId);
}

