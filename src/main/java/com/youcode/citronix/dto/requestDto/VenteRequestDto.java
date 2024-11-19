package com.youcode.citronix.dto.requestDto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VenteRequestDto {

    private LocalDate dateVente;
    private Double prixUnitaire;
    private Long recolteId;
    private String client;
}
