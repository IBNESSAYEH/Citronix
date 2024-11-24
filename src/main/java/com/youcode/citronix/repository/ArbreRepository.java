package com.youcode.citronix.repository;

import com.youcode.citronix.entity.Arbre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArbreRepository extends JpaRepository<Arbre, Long> {
}
