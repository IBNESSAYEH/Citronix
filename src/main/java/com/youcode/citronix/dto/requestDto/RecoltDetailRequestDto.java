package com.youcode.citronix.dto.requestDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RecoltDetailRequestDto {
    @NotNull(message = "recolteId ne peut pas etre null")
    private long recolteId;
    @NotNull(message = "arbreId ne peut pas etre null")
    private long arbreId;


}
