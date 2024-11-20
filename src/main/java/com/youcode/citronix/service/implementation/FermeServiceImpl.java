package com.youcode.citronix.service.implementation;

import com.youcode.citronix.dto.requestDto.FermeRequestDto;
import com.youcode.citronix.dto.responseDto.FermeResponseDto;
import com.youcode.citronix.entity.Ferme;
import com.youcode.citronix.repository.FermeRepository;
import com.youcode.citronix.service.FermeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FermeServiceImpl implements FermeService {

    @Autowired
    private FermeRepository fermeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public FermeResponseDto createFerme(FermeRequestDto fermeRequestDto) {
        Ferme ferme = modelMapper.map(fermeRequestDto, Ferme.class);
        ferme.setDateCreation(LocalDate.now());
        Ferme createdFerme = fermeRepository.save(ferme);
        return modelMapper.map(createdFerme, FermeResponseDto.class);
    }

    @Override
    public List<FermeResponseDto> getAllFermes() {
        List<Ferme> fermes = fermeRepository.findAll();
        return fermes.stream()
                .map(ferme -> modelMapper.map(ferme, FermeResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public FermeResponseDto getFermeById(long id) {
        Ferme ferme = fermeRepository.findById(id).orElseThrow(() -> new RuntimeException("Ferme not found"));
        return modelMapper.map(ferme, FermeResponseDto.class);
    }

    @Override
    public FermeResponseDto updateFerme(long id, FermeRequestDto fermeRequestDto) {
        Ferme ferme = fermeRepository.findById(id).orElseThrow(() -> new RuntimeException("Ferme not found"));
        ferme.setNom(fermeRequestDto.getNom());
        ferme.setLocalisation(fermeRequestDto.getLocalisation());
        ferme.setSuperficie(fermeRequestDto.getSuperficie());
        ferme = fermeRepository.save(ferme);
        return modelMapper.map(ferme, FermeResponseDto.class);
    }

    @Override
    public void deleteFerme(long id) {
        Ferme ferme = fermeRepository.findById(id).orElseThrow(() -> new RuntimeException("Ferme not found"));
        fermeRepository.delete(ferme);
    }
}
