package com.youcode.citronix.dto.requestDto;

import com.youcode.citronix.entity.Ferme;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;
@Data
public class ChampRequestDto {

    @NotBlank(message = "nom du champ ne doivent pas etre vide")
    private String nom;
    @NotNull(message = "nom du champ ne doivent pas etre null")
    @Min(1)
    private Double superficie;
    @NotNull(message = "nom du champ ne doivent pas etre null")
    private Long fermeId;
}
