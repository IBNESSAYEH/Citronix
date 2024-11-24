package com.youcode.citronix.dto.requestDto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ArbreRequestDto {

    @NotNull(message = "La date de plantation ne peut pas etre nulle")
    private LocalDate dateDePlantation;

    @NotNull(message = "Champ id cannot be null")
    private long champId;
}

