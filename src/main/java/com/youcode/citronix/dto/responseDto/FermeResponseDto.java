package com.youcode.citronix.dto.responseDto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

public class FermeResponseDto {

    private long id;

    private String nom;

    private String localisation;

    private String superficie;

    private LocalDate dateCreation;
}
