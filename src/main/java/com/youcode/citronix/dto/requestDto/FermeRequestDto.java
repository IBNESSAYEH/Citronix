package com.youcode.citronix.dto.requestDto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FermeRequestDto {

    @NotNull(message = "le nom du ferme ne peut pas etre null")
    @NotBlank(message = "le nom du ferme ne peut pas etre vide")
    @Size(min = 2, max = 255)
    private String nom;

    @NotNull(message = "la localisation du ferme ne peut pas etre null")
    @NotBlank(message = "la localisation du ferme ne peut pas etre vide")
    @Size(min = 2, max = 255)
    private String localisation;

    @NotNull(message = "la superficie du ferme ne peut pas etre null")
    @Min(3)
    private Double superficie;

}
