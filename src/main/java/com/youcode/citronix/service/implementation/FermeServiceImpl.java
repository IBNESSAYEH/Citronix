package com.youcode.citronix.service.implementation;

import com.youcode.citronix.dto.requestDto.FermeRequestDto;
import com.youcode.citronix.dto.responseDto.FermeResponseDto;
import com.youcode.citronix.entity.Ferme;
import com.youcode.citronix.mappers.FermeMapper;
import com.youcode.citronix.repository.FermeRepository;
import com.youcode.citronix.service.FermeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class FermeServiceImpl implements FermeService {

    @Autowired
    private FermeMapper fermeMapper;

    @Autowired
    private FermeRepository fermeRepository;

    @Override
    public FermeResponseDto createFerme(FermeRequestDto fermeRequestDto) {
        Ferme ferme = fermeMapper.FermeRequestDtotoFerme(fermeRequestDto);
        ferme.setDateCreation(LocalDate.now());
        Ferme createdFerme = fermeRepository.save(ferme);
        return fermeMapper.toResponseDto(createdFerme);
    }
}
