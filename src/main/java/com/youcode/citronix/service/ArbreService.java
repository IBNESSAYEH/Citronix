package com.youcode.citronix.service;

import com.youcode.citronix.dto.requestDto.ArbreRequestDto;
import com.youcode.citronix.dto.responseDto.ArbreResponseDto;

import java.util.List;

public interface ArbreService {
    ArbreResponseDto createArbre(ArbreRequestDto arbreRequestDto);
    List<ArbreResponseDto> getAllArbres();
    ArbreResponseDto getArbreById(int id);
    ArbreResponseDto updateArbre(int id, ArbreRequestDto arbreRequestDto);
    void deleteArbre(int id);
}
