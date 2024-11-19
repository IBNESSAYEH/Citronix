package com.youcode.citronix.service.implementation;

import com.youcode.citronix.dto.requestDto.VenteRequestDto;
import com.youcode.citronix.dto.responseDto.VenteResponseDto;
import com.youcode.citronix.entity.Vente;
import com.youcode.citronix.mappers.VenteMapper;
import com.youcode.citronix.repository.VenteRepository;
import com.youcode.citronix.repository.RecolteRepository;
import com.youcode.citronix.service.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VenteServiceImpl implements VenteService {

    @Autowired
    private VenteRepository venteRepository;

    @Autowired
    private RecolteRepository recolteRepository;

    @Autowired
    private VenteMapper venteMapper;

    @Override
    public VenteResponseDto createVente(VenteRequestDto venteRequestDto) {
        Vente vente = venteMapper.toEntity(venteRequestDto);
        vente.setRecolte(recolteRepository.findById(venteRequestDto.getRecolteId())
                .orElseThrow(() -> new RuntimeException("Recolte not found")));
        vente = venteRepository.save(vente);
        return venteMapper.toResponseDto(vente);
    }

    @Override
    public List<VenteResponseDto> getAllVentes() {
        List<Vente> ventes = venteRepository.findAll();
        return ventes.stream()
                .map(venteMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public VenteResponseDto getVenteById(Long id) {
        Vente vente = venteRepository.findById(id).orElseThrow(() -> new RuntimeException("Vente not found"));
        return venteMapper.toResponseDto(vente);
    }

    @Override
    public VenteResponseDto updateVente(Long id, VenteRequestDto venteRequestDto) {
        Vente vente = venteRepository.findById(id).orElseThrow(() -> new RuntimeException("Vente not found"));
        vente.setDateVente(venteRequestDto.getDateVente());
        vente.setPrixUnitaire(venteRequestDto.getPrixUnitaire());
        vente.setClient(venteRequestDto.getClient());
        vente.setRecolte(recolteRepository.findById(venteRequestDto.getRecolteId())
                .orElseThrow(() -> new RuntimeException("Recolte not found")));
        vente = venteRepository.save(vente);
        return venteMapper.toResponseDto(vente);
    }

    @Override
    public void deleteVente(Long id) {
        Vente vente = venteRepository.findById(id).orElseThrow(() -> new RuntimeException("Vente not found"));
        venteRepository.delete(vente);
    }
}

