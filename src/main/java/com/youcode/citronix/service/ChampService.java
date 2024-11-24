package com.youcode.citronix.service;

import com.youcode.citronix.dto.requestDto.ChampRequestDto;
import com.youcode.citronix.dto.responseDto.ChampResponseDto;
import com.youcode.citronix.dto.responseDto.FermeResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChampService {
    ChampResponseDto createChamp(ChampRequestDto champRequestDto);
    void deleteChamp(long id);
    ChampResponseDto updateChamp(long id, ChampRequestDto champRequestDto);
    ChampResponseDto getChampById(long id);
    List<ChampResponseDto> getAllChamps();
}
