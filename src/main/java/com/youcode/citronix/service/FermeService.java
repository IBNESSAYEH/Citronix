package com.youcode.citronix.service;

import com.youcode.citronix.dto.requestDto.FermeRequestDto;
import com.youcode.citronix.dto.responseDto.FermeResponseDto;
import com.youcode.citronix.entity.Ferme;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface FermeService {
    FermeResponseDto createFerme(FermeRequestDto fermeRequestDto);
    Page<FermeResponseDto> getAllFermes(int page, int size) ;
    FermeResponseDto getFermeById(long id);
    FermeResponseDto updateFerme(long id, FermeRequestDto fermeRequestDto);
    void deleteFerme(long id);
    List<Ferme> getFermesByCriteria(String nom, String localisation, Double superficie);

}
