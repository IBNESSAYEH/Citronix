package com.youcode.citronix.dto.requestDto;


import com.youcode.citronix.entity.enums.Saison;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RecolteRequestDto {
    @NotNull(message = "dateRecolte ne peut pas etre null")
    private LocalDate dateRecolte;
}
