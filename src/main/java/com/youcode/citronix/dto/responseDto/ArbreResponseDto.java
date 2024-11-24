package com.youcode.citronix.dto.responseDto;

import com.youcode.citronix.entity.enums.ArbreAge;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ArbreResponseDto {
    private int id;
    private LocalDate dateDePlantation;
    private ArbreAge age;
}
