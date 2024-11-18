package com.youcode.citronix.dto.responseDto;

import com.youcode.citronix.entity.Ferme;
import lombok.Data;

import java.util.UUID;
@Data
public class ChampResponseDto {
    private UUID id;
    private String nom;
    private Double superficie;
    private Ferme ferme;
}
