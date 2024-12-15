package com.youcode.citronix.service.implementation;

import com.youcode.citronix.Util.DeterminSaison;
import com.youcode.citronix.dto.requestDto.RecolteRequestDto;
import com.youcode.citronix.dto.responseDto.RecolteResponseDto;
import com.youcode.citronix.entity.Recolte;
import com.youcode.citronix.entity.enums.Saison;
import com.youcode.citronix.exception.recolteException.RecoltNotFoundException;
import com.youcode.citronix.repository.RecolteRepository;
import com.youcode.citronix.service.RecolteService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RecolteServiceImpl implements RecolteService {


    private RecolteRepository recolteRepository;

    private ModelMapper modelMapper;

    @Override
    public RecolteResponseDto createRecolte(RecolteRequestDto recolteRequestDto) {
        Recolte recolte = modelMapper.map(recolteRequestDto, Recolte.class);
          recolte.setSaison(DeterminSaison.defineSaisonFromDateRecolte(recolte.getDateRecolte()));
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
        Recolte recolte = recolteRepository.findById(id).orElseThrow(() -> new RecoltNotFoundException("Recolte not found"));
        return modelMapper.map(recolte, RecolteResponseDto.class);
    }

    @Override
    public RecolteResponseDto updateRecolte(Long id, RecolteRequestDto recolteRequestDto) {
        Recolte recolte = recolteRepository.findById(id)
                .orElseThrow(() -> new RecoltNotFoundException("Recolte not found"));
        recolte.setSaison(DeterminSaison.defineSaisonFromDateRecolte(recolteRequestDto.getDateRecolte()));
         recolte.setDateRecolte(recolteRequestDto.getDateRecolte());
     recolte = recolteRepository.save(recolte);
        return modelMapper.map(recolte, RecolteResponseDto.class);
    }

    @Override
    public void deleteRecolte(Long id) {
        Recolte recolte = recolteRepository.findById(id)
                .orElseThrow(() -> new RecoltNotFoundException("Recolte not found"));
        recolteRepository.delete(recolte);
    }




}
