package com.youcode.citronix.dto.responseDto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ArbreResponseDto {
    private int id;
    private LocalDate datePlantation;
}
