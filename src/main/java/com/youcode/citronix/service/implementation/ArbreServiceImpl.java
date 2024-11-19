package com.youcode.citronix.service.implementation;

import com.youcode.citronix.dto.requestDto.ArbreRequestDto;
import com.youcode.citronix.dto.responseDto.ArbreResponseDto;
import com.youcode.citronix.entity.Arbre;
import com.youcode.citronix.entity.Champ;
import com.youcode.citronix.mappers.ArbreMapper;
import com.youcode.citronix.repository.ArbreRepository;
import com.youcode.citronix.repository.ChampRepository;
import com.youcode.citronix.service.ArbreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArbreServiceImpl implements ArbreService {

    @Autowired
    private ArbreRepository arbreRepository;

    @Autowired
    private ChampRepository champRepository;

    @Autowired
    private ArbreMapper arbreMapper;

    public ArbreResponseDto createArbre(ArbreRequestDto arbreRequestDto) {
        Optional<Champ> champ = champRepository.findById(arbreRequestDto.getChampId());
        if (champ.isEmpty()) {
            throw new RuntimeException("Champ not found with ID: " + arbreRequestDto.getChampId());
        }

        Arbre arbre = arbreMapper.ArbreRequestDtoToArbre(arbreRequestDto);
        arbre.setChamp(champ.get());

        arbre = arbreRepository.save(arbre);
        return arbreMapper.toResponseDto(arbre);
    }

    public List<ArbreResponseDto> getAllArbres() {
        List<Arbre> arbres = arbreRepository.findAll();
        return arbres.stream()
                .map(arbreMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public ArbreResponseDto getArbreById(int id) {
        Arbre arbre = arbreRepository.findById(id).orElseThrow(() -> new RuntimeException("Arbre not found"));
        return arbreMapper.toResponseDto(arbre);
    }

    public ArbreResponseDto updateArbre(int id, ArbreRequestDto arbreRequestDto) {
        Arbre arbre = arbreRepository.findById(id).orElseThrow(() -> new RuntimeException("Arbre not found"));
        Optional<Champ> champ = champRepository.findById(arbreRequestDto.getChampId());
        if (champ.isEmpty()) {
            throw new RuntimeException("Champ not found with ID: " + arbreRequestDto.getChampId());
        }

        arbre.setDatePlantation(arbreRequestDto.getDatePlantation());
        arbre.setChamp(champ.get());

        arbre = arbreRepository.save(arbre);
        return arbreMapper.toResponseDto(arbre);
    }

    public void deleteArbre(int id) {
        Arbre arbre = arbreRepository.findById(id).orElseThrow(() -> new RuntimeException("Arbre not found"));
        arbreRepository.delete(arbre);
    }
}

