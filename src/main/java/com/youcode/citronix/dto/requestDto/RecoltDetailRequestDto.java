package com.youcode.citronix.dto.requestDto;
import lombok.Data;

@Data
public class RecoltDetailRequestDto {

    private Long recolteId;
    private Long arbreId;
    private Double quantite;
}
