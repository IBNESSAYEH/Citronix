package com.youcode.citronix.repository;

import com.youcode.citronix.entity.Champ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;
@Repository
public interface ChampRepository extends JpaRepository<Champ, Long>{

}
