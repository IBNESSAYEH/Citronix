package com.youcode.citronix.service;

import com.youcode.citronix.dto.requestDto.RecolteRequestDto;
import com.youcode.citronix.dto.responseDto.RecolteResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface RecolteService {

    RecolteResponseDto createRecolte(RecolteRequestDto recolteRequestDto);

    List<RecolteResponseDto> getAllRecoltes();

    RecolteResponseDto getRecolteById(Long id);

    RecolteResponseDto updateRecolte(Long id, RecolteRequestDto recolteRequestDto);

    void deleteRecolte(Long id);
}

