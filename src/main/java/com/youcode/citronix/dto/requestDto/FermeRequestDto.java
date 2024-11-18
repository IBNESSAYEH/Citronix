package com.youcode.citronix.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FermeRequestDto {

    @NotNull(message = "le nom du ferme ne peut pas etre null")
    @NotBlank(message = "le nom du ferme ne peut pas etre vide")
    private String nom;

    @NotNull(message = "la localisation du ferme ne peut pas etre null")
    @NotBlank(message = "la localisation du ferme ne peut pas etre vide")
    private String localisation;

    @NotNull(message = "la superficie du ferme ne peut pas etre null")
    private String superficie;
}
