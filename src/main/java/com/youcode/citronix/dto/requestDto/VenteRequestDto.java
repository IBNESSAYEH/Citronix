package com.youcode.citronix.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class VenteRequestDto {
    @NotNull(message = "dateVente ne peut pas etre null")
    private LocalDate dateVente;
    @NotNull(message = "dateVente ne peut pas etre null")
    private Double prixUnitaire;
    @NotNull(message = "recolteID ne peut pas etre null")
    private long recolteID;
    @NotNull(message = "client ne peut pas etre null")
    @NotBlank(message = "client ne peut pas etre vide")
    private String client;


}

