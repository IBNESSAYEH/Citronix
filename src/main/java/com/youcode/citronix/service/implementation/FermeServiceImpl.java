package com.youcode.citronix.service.implementation;

import com.youcode.citronix.dto.requestDto.FermeRequestDto;
import com.youcode.citronix.dto.responseDto.FermeResponseDto;
import com.youcode.citronix.entity.Ferme;
import com.youcode.citronix.exception.fermeExceptions.FermeNotFoundException;
import com.youcode.citronix.repository.FermeRepository;
import com.youcode.citronix.service.FermeService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Service
@AllArgsConstructor
public class FermeServiceImpl implements FermeService {

    private FermeRepository fermeRepository;

    private ModelMapper modelMapper;

    @Override
    public FermeResponseDto createFerme(FermeRequestDto fermeRequestDto) {
        Ferme ferme = modelMapper.map(fermeRequestDto, Ferme.class);
        ferme.setSuperficieExploitee(0.0);
         ferme.setNombreChamp(10);
      Ferme createdFerme = fermeRepository.save(ferme);
        return modelMapper.map(createdFerme, FermeResponseDto.class);
    }

    @Override
    public Page<FermeResponseDto> getAllFermes(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
         Page<Ferme> fermes = fermeRepository.findAll(pageable);
        return fermes.map(ferme -> modelMapper.map(ferme, FermeResponseDto.class));
    }

    @Override
    public FermeResponseDto getFermeById(long id) {
        Ferme ferme = fermeRepository.findById(id).orElseThrow(() -> new FermeNotFoundException("Ferme not found"));
        return modelMapper.map(ferme, FermeResponseDto.class);
    }

    @Override
    public FermeResponseDto updateFerme(long id, FermeRequestDto fermeRequestDto) {
        Ferme ferme = fermeRepository.findById(id).orElseThrow(() -> new FermeNotFoundException("Ferme not found"));
        ferme.setNom(fermeRequestDto.getNom());
          ferme.setLocalisation(fermeRequestDto.getLocalisation());
        ferme.setSuperficie(fermeRequestDto.getSuperficie());
       ferme = fermeRepository.save(ferme);
        return modelMapper.map(ferme, FermeResponseDto.class);
    }

    @Override
    public void deleteFerme(long id) {
        Ferme ferme = fermeRepository.findById(id).orElseThrow(() -> new FermeNotFoundException("Ferme not found"));
        fermeRepository.delete(ferme);
    }

    @Override
    public List<Ferme> getFermesByCriteria(String nom, String localisation, Double superficie) {
        if (nom != null && localisation != null && superficie != null) {
            return fermeRepository.findByNomAndLocalisationAndSuperficie(nom, localisation, superficie);
         } else if (nom != null && localisation != null) {
            return fermeRepository.findByNomAndLocalisation(nom, localisation);
            } else if (nom != null) {
            return fermeRepository.findByNom(nom);
        } else if (localisation != null) {
            return fermeRepository.findByLocalisation(localisation);
      } else if (superficie != null) {
            return fermeRepository.findBySuperficie(superficie);
        } else {
            return fermeRepository.findAll();
      }
    }
}
