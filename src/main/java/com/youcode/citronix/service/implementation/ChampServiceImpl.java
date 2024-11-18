package com.youcode.citronix.service.implementation;

import com.youcode.citronix.dto.requestDto.ChampRequestDto;
import com.youcode.citronix.dto.responseDto.ChampResponseDto;
import com.youcode.citronix.entity.Champ;
import com.youcode.citronix.entity.Ferme;
import com.youcode.citronix.mappers.ChampMapper;
import com.youcode.citronix.repository.ChampRepository;
import com.youcode.citronix.repository.FermeRepository;
import com.youcode.citronix.service.ChampService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ChampServiceImpl implements ChampService {

    @Autowired
    private ChampRepository champRepository;

    @Autowired
    private ChampMapper champMapper;
    @Autowired
    private FermeRepository fermeRepository;

    @Override
    public ChampResponseDto createChamp(ChampRequestDto champRequestDto) {
        UUID champIdFromRequest = champRequestDto.getFerme().getId();
        Optional<Ferme> optionalFerme = fermeRepository.findById(champIdFromRequest);

        if (optionalFerme.isPresent()) {
            Champ champCreated = champMapper.ChampRequestDtoToChamp(champRequestDto);
            Champ createdChamp = champRepository.save(champCreated);
            ChampResponseDto champResponseDto = champMapper.champToChampResponseDto(createdChamp);
            champResponseDto.setFerme(null);
            return champResponseDto;
        } else {
            throw new RuntimeException("Ferme with id " + champIdFromRequest + " not found");
        }
    }

}
