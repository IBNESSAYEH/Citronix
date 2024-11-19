package com.youcode.citronix.dto.requestDto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ArbreRequestDto {

    @NotNull(message = "Date of plantation cannot be null")
    private LocalDate datePlantation;

    @NotNull(message = "Champ ID cannot be null")
    private long champId;
}

