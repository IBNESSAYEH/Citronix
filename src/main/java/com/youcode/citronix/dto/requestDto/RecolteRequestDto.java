package com.youcode.citronix.dto.requestDto;


import com.youcode.citronix.entity.enums.Saison;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RecolteRequestDto {

    private Saison saison;
    private LocalDate dateRecolte;
    private Double quantiteTotale;
    private long fermeId;
}
