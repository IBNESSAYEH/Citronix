package com.youcode.citronix.dto.responseDto;

import com.youcode.citronix.dto.requestDto.FermeRequestDto;
import com.youcode.citronix.entity.Ferme;
import lombok.Data;

import java.util.UUID;
@Data
public class ChampResponseDto {
    private long id;
    private String nom;
    private Double superficie;
    private Double nbrArbre;
}
