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
import java.util.List;
import java.util.stream.Collectors;

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
    @Override
    public List<FermeResponseDto> getAllFermes() {
        List<Ferme> fermes = fermeRepository.findAll();
        return fermes.stream()
                .map(fermeMapper::toResponseDto)
                .collect(Collectors.toList());
    }
    @Override
    public FermeResponseDto getFermeById(long id) {
        Ferme ferme = fermeRepository.findById(id).orElseThrow(() -> new RuntimeException("Ferme not found"));
        return fermeMapper.toResponseDto(ferme);
    }
    @Override
    public FermeResponseDto updateFerme(long id, FermeRequestDto fermeRequestDto) {
        Ferme ferme = fermeRepository.findById(id).orElseThrow(() -> new RuntimeException("Ferme not found"));
        ferme.setNom(fermeRequestDto.getNom());
        ferme.setLocalisation(fermeRequestDto.getLocalisation());
        ferme.setSuperficie(fermeRequestDto.getSuperficie());
        ferme = fermeRepository.save(ferme);
        return fermeMapper.toResponseDto(ferme);
    }

    @Override
    public void deleteFerme(long id) {
        Ferme ferme = fermeRepository.findById(id).orElseThrow(() -> new RuntimeException("Ferme not found"));
        fermeRepository.delete(ferme);
    }

}
