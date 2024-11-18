package com.youcode.citronix.service;

import com.youcode.citronix.dto.requestDto.ChampRequestDto;
import com.youcode.citronix.dto.responseDto.ChampResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface ChampService {
    ChampResponseDto createChamp(ChampRequestDto champRequestDto);
}
