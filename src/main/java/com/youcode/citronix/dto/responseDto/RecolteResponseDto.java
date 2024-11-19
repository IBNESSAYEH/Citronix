package com.youcode.citronix.dto.responseDto;


import com.youcode.citronix.entity.enums.Saison;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RecolteResponseDto {

    private Long id;
    private Saison saison;
    private LocalDate dateRecolte;
    private Double quantiteTotale;
}