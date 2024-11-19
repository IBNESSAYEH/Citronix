package com.youcode.citronix.dto.responseDto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VenteResponseDto {

    private Long id;
    private LocalDate dateVente;
    private Double prixUnitaire;
    private String client;
}

