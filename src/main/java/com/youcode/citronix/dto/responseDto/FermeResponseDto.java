package com.youcode.citronix.dto.responseDto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class FermeResponseDto {

    private UUID id;

    private String nom;

    private String localisation;

    private Double superficie;

    private LocalDate dateCreation;


}
