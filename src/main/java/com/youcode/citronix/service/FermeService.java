package com.youcode.citronix.service;

import com.youcode.citronix.dto.requestDto.FermeRequestDto;
import com.youcode.citronix.dto.responseDto.FermeResponseDto;
import com.youcode.citronix.entity.Ferme;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface FermeService {
    FermeResponseDto createFerme(FermeRequestDto fermeRequestDto);
}
