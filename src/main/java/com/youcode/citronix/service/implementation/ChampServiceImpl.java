package com.youcode.citronix.service.implementation;

import com.youcode.citronix.dto.requestDto.ChampRequestDto;
import com.youcode.citronix.dto.responseDto.ChampResponseDto;
import com.youcode.citronix.entity.Champ;
import com.youcode.citronix.entity.Ferme;
import com.youcode.citronix.repository.ChampRepository;
import com.youcode.citronix.repository.FermeRepository;
import com.youcode.citronix.service.ChampService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChampServiceImpl implements ChampService {

    @Autowired
    private ChampRepository champRepository;

    @Autowired
    private FermeRepository fermeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ChampResponseDto createChamp(ChampRequestDto champRequestDto) {
        long fermeIdFromRequest = champRequestDto.getFermeId();
        Optional<Ferme> optionalFerme = fermeRepository.findById(fermeIdFromRequest);

        if (optionalFerme.isPresent()) {
            Champ champCreated = modelMapper.map(champRequestDto, Champ.class);
            champCreated.setFerme(optionalFerme.get());
            Champ createdChamp = champRepository.save(champCreated);
            ChampResponseDto champResponseDto = modelMapper.map(createdChamp, ChampResponseDto.class);
            champResponseDto.setFerme(null);
            return champResponseDto;
        } else {
            throw new RuntimeException("Ferme with id " + fermeIdFromRequest + " not found");
        }
    }

    @Override
    @Transactional
    public List<ChampResponseDto> getAllChamps() {
        List<Champ> champs = champRepository.findAll();
        return champs.stream()
                .map(champ -> modelMapper.map(champ, ChampResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ChampResponseDto getChampById(long id) {
        Champ champ = champRepository.findById(id).orElseThrow(() -> new RuntimeException("Champ not found"));
        return modelMapper.map(champ, ChampResponseDto.class);
    }

    @Override
    public ChampResponseDto updateChamp(long id, ChampRequestDto champRequestDto) {
        Champ champ = champRepository.findById(id).orElseThrow(() -> new RuntimeException("Champ not found"));
        champ.setNom(champRequestDto.getNom());
        champ.setSuperficie(champRequestDto.getSuperficie());
        champ.setFerme(fermeRepository.findById(champRequestDto.getFermeId())
                .orElseThrow(() -> new RuntimeException("Ferme not found")));
        champ = champRepository.save(champ);
        return modelMapper.map(champ, ChampResponseDto.class);
    }

    @Override
    public void deleteChamp(long id) {
        Champ champ = champRepository.findById(id).orElseThrow(() -> new RuntimeException("Champ not found"));
        champRepository.delete(champ);
    }
}
