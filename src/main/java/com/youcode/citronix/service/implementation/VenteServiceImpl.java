package com.youcode.citronix.service.implementation;

import com.youcode.citronix.dto.requestDto.VenteRequestDto;
import com.youcode.citronix.dto.responseDto.VenteResponseDto;
import com.youcode.citronix.entity.RecoltDetail;
import com.youcode.citronix.entity.Recolte;
import com.youcode.citronix.entity.Vente;
import com.youcode.citronix.exception.fermeExceptions.FermeNotFoundException;
import com.youcode.citronix.exception.recolteException.RecoltNotFoundException;
import com.youcode.citronix.repository.VenteRepository;
import com.youcode.citronix.repository.RecolteRepository;
import com.youcode.citronix.service.VenteService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VenteServiceImpl implements VenteService {


    private VenteRepository venteRepository;


    private RecolteRepository recolteRepository;

    private ModelMapper modelMapper;

    @Override
    @Transactional
    public VenteResponseDto createVente(VenteRequestDto venteRequestDto) {
        Vente vente = modelMapper.map(venteRequestDto, Vente.class);
          Recolte recolte = recolteRepository.findById(venteRequestDto.getRecolteID()).orElseThrow(() -> new RecoltNotFoundException("Recolte not found"));
        vente.setRecolte(recolte);
        double quantiteTotale = recolte.getRecoltDetails().stream()
                  .filter(recoltDetail -> recoltDetail.getRecolte().getId() == recolte.getId())
                .mapToDouble(RecoltDetail::getQuantite)
                .sum();

        vente.setRevenue(quantiteTotale * vente.getPrixUnitaire());
         vente = venteRepository.save(vente);
        return modelMapper.map(vente, VenteResponseDto.class);
    }



    @Override
    public List<VenteResponseDto> getAllVentes() {
        List<Vente> ventes = venteRepository.findAll();
        return ventes.stream()
                 .map(vente -> modelMapper.map(vente, VenteResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public VenteResponseDto getVenteById(Long id) {
        Vente vente = venteRepository.findById(id).orElseThrow(() -> new FermeNotFoundException("Vente not found"));
        return modelMapper.map(vente, VenteResponseDto.class);
    }

    @Override
    public VenteResponseDto updateVente(Long id, VenteRequestDto venteRequestDto) {
        Vente vente = venteRepository.findById(id).orElseThrow(() -> new RecoltNotFoundException("Vente not found"));
      vente.setDateVente(venteRequestDto.getDateVente());
        vente.setPrixUnitaire(venteRequestDto.getPrixUnitaire());
          vente.setClient(venteRequestDto.getClient());
        vente.setRecolte(recolteRepository.findById(venteRequestDto.getRecolteID())
                .orElseThrow(() -> new RecoltNotFoundException("Recolte not found")));
        vente = venteRepository.save(vente);
        return modelMapper.map(vente, VenteResponseDto.class);
    }

    @Override
    public void deleteVente(Long id) {
         Vente vente = venteRepository.findById(id).orElseThrow(() -> new RecoltNotFoundException("Vente not found"));
        venteRepository.delete(vente);
    }
}
