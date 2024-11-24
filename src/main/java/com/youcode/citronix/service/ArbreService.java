package com.youcode.citronix.service;

import com.youcode.citronix.dto.requestDto.ArbreRequestDto;
import com.youcode.citronix.dto.responseDto.ArbreResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ArbreService {
    ArbreResponseDto createArbre(ArbreRequestDto arbreRequestDto);
    List<ArbreResponseDto> getAllArbres();
    ArbreResponseDto getArbreById(long id);
    ArbreResponseDto updateArbre(long id, ArbreRequestDto arbreRequestDto);
    void deleteArbre(long id);
}
