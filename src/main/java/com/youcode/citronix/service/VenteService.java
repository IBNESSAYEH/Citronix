package com.youcode.citronix.service;

import com.youcode.citronix.dto.requestDto.VenteRequestDto;
import com.youcode.citronix.dto.responseDto.VenteResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface VenteService {

    VenteResponseDto createVente(VenteRequestDto venteRequestDto);

    List<VenteResponseDto> getAllVentes();

    VenteResponseDto getVenteById(Long id);

    VenteResponseDto updateVente(Long id, VenteRequestDto venteRequestDto);

    void deleteVente(Long id);
}

