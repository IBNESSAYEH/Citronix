package com.youcode.citronix.repository;

import com.youcode.citronix.entity.RecoltDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecoltDetailRepository extends JpaRepository<RecoltDetail, Long> {
}

