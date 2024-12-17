package com.youcode.citronix.service.implementation;

import com.youcode.citronix.dto.requestDto.FermeRequestDto;
import com.youcode.citronix.dto.responseDto.FermeResponseDto;
import com.youcode.citronix.entity.Ferme;
import com.youcode.citronix.exception.fermeExceptions.FermeNotFoundException;
import com.youcode.citronix.repository.FermeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FermeServiceImplTest {

    @InjectMocks
    private FermeServiceImpl fermeService;

    @Mock
    private FermeRepository fermeRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void createFerme_Success() {
        FermeRequestDto requestDto = new FermeRequestDto();
         requestDto.setNom("Testt f erme");
            requestDto.setLocalisation("casa");
        requestDto.setSuperficie(100.0);
      Ferme ferme = new Ferme();
        ferme.setNom(requestDto.getNom());
          ferme.setLocalisation(requestDto.getLocalisation());
        ferme.setSuperficie(requestDto.getSuperficie());
       when(modelMapper.map(requestDto, Ferme.class)).thenReturn(ferme);
            when(fermeRepository.save(any(Ferme.class))).thenReturn(ferme);
        when(modelMapper.map(ferme, FermeResponseDto.class)).thenReturn(new FermeResponseDto());
          FermeResponseDto response = fermeService.createFerme(requestDto);
        assertNotNull(response);
        verify(fermeRepository, times(1)).save(any(Ferme.class));
    }

    @Test
    void getAllFermes_Success() {

          Ferme ferme = new Ferme();
       Page<Ferme> fermes = new PageImpl<>(Collections.singletonList(ferme));
        when(fermeRepository.findAll(any(Pageable.class))).thenReturn(fermes);
            when(modelMapper.map(any(Ferme.class), eq(FermeResponseDto.class))).thenReturn(new FermeResponseDto());
        Page<FermeResponseDto> response = fermeService.getAllFermes(0, 10);
      assertNotNull(response);
          assertEquals(1, response.getTotalElements());
        verify(fermeRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void getFermeById_Success() {

        long id = 1L;
          Ferme ferme = new Ferme();
             ferme.setId(id);
        when(fermeRepository.findById(id)).thenReturn(Optional.of(ferme));
          when(modelMapper.map(ferme, FermeResponseDto.class)).thenReturn(new FermeResponseDto());
         FermeResponseDto response = fermeService.getFermeById(id);
        assertNotNull(response);
      verify(fermeRepository, times(1)).findById(id);
    }

    @Test
    void getFermeById_NotFound() {

        long id = 1L;
         when(fermeRepository.findById(id)).thenReturn(Optional.empty());
            assertThrows(FermeNotFoundException.class, () -> fermeService.getFermeById(id));
          verify(fermeRepository, times(1)).findById(id);
    }

    @Test
    void updateFerme_Success() {

        long id = 1L;
        FermeRequestDto requestDto = new FermeRequestDto();
        requestDto.setNom("updatee Ferme");
          requestDto.setLocalisation("settat");
         requestDto.setSuperficie(200.0);
            Ferme ferme = new Ferme();
        ferme.setId(id);
         when(fermeRepository.findById(id)).thenReturn(Optional.of(ferme));
     when(fermeRepository.save(any(Ferme.class))).thenReturn(ferme);
        when(modelMapper.map(ferme, FermeResponseDto.class)).thenReturn(new FermeResponseDto());

         FermeResponseDto response = fermeService.updateFerme(id, requestDto);

        assertNotNull(response);
          verify(fermeRepository, times(1)).findById(id);
        verify(fermeRepository, times(1)).save(any(Ferme.class));
    }

    @Test
    void deleteFerme_Success() {
        long id = 1L;
        Ferme ferme = new Ferme();
          when(fermeRepository.findById(id)).thenReturn(Optional.of(ferme));
         doNothing().when(fermeRepository).delete(ferme);
     fermeService.deleteFerme(id);
        verify(fermeRepository, times(1)).findById(id);
        verify(fermeRepository, times(1)).delete(ferme);
    }

    @Test
    void deleteFerme_NotFound() {

        long id = 1L;
           when(fermeRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(FermeNotFoundException.class, () -> fermeService.deleteFerme(id));
        verify(fermeRepository, times(1)).findById(id);
    }

    @Test
    void getFermesByCriteria_NomOnly() {

        String nom = "Test feerme";
         List<Ferme> fermes = Collections.singletonList(new Ferme());
       when(fermeRepository.findByNom(nom)).thenReturn(fermes);

        List<Ferme> response = fermeService.getFermesByCriteria(nom, null, null);

        assertNotNull(response);
          assertEquals(1, response.size());
        verify(fermeRepository, times(1)).findByNom(nom);
    }

    @Test
    void getFermesByCriteria_AllCriteria() {

        String nom = "Test fermee";
         String localisation = "Casa";
            Double superficie = 100.0;
        List<Ferme> fermes = Collections.singletonList(new Ferme());
        when(fermeRepository.findByNomAndLocalisationAndSuperficie(nom, localisation, superficie)).thenReturn(fermes);
      List<Ferme> response = fermeService.getFermesByCriteria(nom, localisation, superficie);
        assertNotNull(response);
        assertEquals(1, response.size());
        verify(fermeRepository, times(1)).findByNomAndLocalisationAndSuperficie(nom, localisation, superficie);
    }
}
