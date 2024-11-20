package com.youcode.citronix.service.implementation;

import com.youcode.citronix.dto.requestDto.RecolteRequestDto;
import com.youcode.citronix.dto.responseDto.RecolteResponseDto;
import com.youcode.citronix.entity.Recolte;
import com.youcode.citronix.repository.RecolteRepository;
import com.youcode.citronix.service.RecolteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecolteServiceImpl implements RecolteService {

    @Autowired
    private RecolteRepository recolteRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RecolteResponseDto createRecolte(RecolteRequestDto recolteRequestDto) {
        Recolte recolte = modelMapper.map(recolteRequestDto, Recolte.class);
        recolte = recolteRepository.save(recolte);
        return modelMapper.map(recolte, RecolteResponseDto.class);
    }

    @Override
    public List<RecolteResponseDto> getAllRecoltes() {
        List<Recolte> recoltes = recolteRepository.findAll();
        return recoltes.stream()
                .map(recolte -> modelMapper.map(recolte, RecolteResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public RecolteResponseDto getRecolteById(Long id) {
        Recolte recolte = recolteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recolte not found"));
        return modelMapper.map(recolte, RecolteResponseDto.class);
    }

    @Override
    public RecolteResponseDto updateRecolte(Long id, RecolteRequestDto recolteRequestDto) {
        Recolte recolte = recolteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recolte not found"));
        recolte.setSaison(recolteRequestDto.getSaison());
        recolte.setDateRecolte(recolteRequestDto.getDateRecolte());
        recolte.setQuantiteTotale(recolteRequestDto.getQuantiteTotale());
        recolte = recolteRepository.save(recolte);
        return modelMapper.map(recolte, RecolteResponseDto.class);
    }

    @Override
    public void deleteRecolte(Long id) {
        Recolte recolte = recolteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recolte not found"));
        recolteRepository.delete(recolte);
    }
}
