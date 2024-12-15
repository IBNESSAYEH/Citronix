package com.youcode.citronix.service.implementation;

import com.youcode.citronix.dto.requestDto.RecoltDetailRequestDto;
import com.youcode.citronix.dto.responseDto.RecoltDetailResponseDto;
import com.youcode.citronix.dto.responseDto.RecolteResponseDto;
import com.youcode.citronix.entity.Arbre;
import com.youcode.citronix.entity.RecoltDetail;
import com.youcode.citronix.entity.Recolte;
import com.youcode.citronix.entity.enums.ArbreAge;
import com.youcode.citronix.exception.RecolteDetailException.DoubleRecoltingInThSAmeSAion;
import com.youcode.citronix.exception.arbreException.ArbreNotFoundException;
import com.youcode.citronix.exception.recolteException.RecoltNotFoundException;
import com.youcode.citronix.repository.RecoltDetailRepository;
import com.youcode.citronix.repository.RecolteRepository;
import com.youcode.citronix.repository.ArbreRepository;
import com.youcode.citronix.service.RecoltDetailService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RecoltDetailServiceImpl implements RecoltDetailService {


    private RecoltDetailRepository recoltDetailRepository;

    private ModelMapper modelMapper;
    private RecolteRepository recolteRepository;

    private ArbreRepository arbreRepository;

    @Override
    public RecoltDetailResponseDto createRecoltDetail(RecoltDetailRequestDto recoltDetailRequestDto) {
        RecoltDetail recoltDetail = new RecoltDetail();
        Arbre arbre = arbreRepository.findById(recoltDetailRequestDto.getArbreId()).orElseThrow(() -> new ArbreNotFoundException("Arbre not found "));
       return saveRecolteDetail(arbre, recoltDetail, recoltDetailRequestDto);
    }
    @Override
    public RecoltDetailResponseDto updateRecoltDetail(Long id, RecoltDetailRequestDto recoltDetailRequestDto) {
        RecoltDetail recoltDetail = recoltDetailRepository.findById(id).orElseThrow(() -> new RecoltNotFoundException("recoltDetail not found "));
         Arbre arbre = arbreRepository.findById(recoltDetailRequestDto.getArbreId()).orElseThrow(() -> new ArbreNotFoundException("Arbre not found Exception"));
        return saveRecolteDetail(arbre, recoltDetail, recoltDetailRequestDto);

    }

    private RecoltDetailResponseDto saveRecolteDetail(Arbre arbre, RecoltDetail recoltDetail,RecoltDetailRequestDto recoltDetailRequestDto){
        Recolte recolte = recolteRepository.findById(recoltDetailRequestDto.getRecolteId()).orElseThrow(() -> new RecoltNotFoundException("recolte not found Exception"));
         checkIfTheArbreNotRolledInTheSameSaison(recoltDetailRequestDto, recolte);
        recoltDetail.setArbre(arbre);
      recoltDetail.setRecolte(recolte);
        recoltDetail.setQuantite(recoltDetail.getArbre().getAge() == ArbreAge.JEUNE ? 2.5 : recoltDetail.getArbre().getAge() == ArbreAge.MATURE ? 12.0 : recoltDetail.getArbre().getAge() == ArbreAge.VIEUX ? 20.0 : 0.0);
        recoltDetail = recoltDetailRepository.save(recoltDetail);
        return modelMapper.map(recoltDetail, RecoltDetailResponseDto.class);
    }
    private void checkIfTheArbreNotRolledInTheSameSaison(RecoltDetailRequestDto recoltDetailRequestDto, Recolte recolte){
        Optional<RecoltDetail> lastRecoltDetailOpt = recoltDetailRepository.findLastByArbreId(recoltDetailRequestDto.getArbreId());
        if (lastRecoltDetailOpt.isPresent()) {
            RecoltDetail lastRecoltDetail = lastRecoltDetailOpt.get();
              LocalDate lastRecolteDate = lastRecoltDetail.getRecolte().getDateRecolte();
            LocalDate newRecolteDate = recolte.getDateRecolte();
            if (lastRecoltDetail.getRecolte().getSaison().equals(recolte.getSaison()) &&
                    lastRecolteDate.getYear() == newRecolteDate.getYear()) {
                throw new DoubleRecoltingInThSAmeSAion("you cant recolte an arbre in the same saion");
            }
        }
    }



    @Override
    public List<RecoltDetailResponseDto> getAllRecoltDetails() {
        List<RecoltDetail> recoltDetails = recoltDetailRepository.findAll();
        return recoltDetails.stream()
                .map(recoltDetail -> modelMapper.map(recoltDetail, RecoltDetailResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public RecoltDetailResponseDto getRecoltDetailById(Long id) {
        RecoltDetail recoltDetail = recoltDetailRepository.findById(id)
                .orElseThrow(() -> new RecoltNotFoundException("RecoltDetail not found"));
        return modelMapper.map(recoltDetail, RecoltDetailResponseDto.class);
    }

    @Override
    public void deleteRecoltDetail(Long id) {
        RecoltDetail recoltDetail = recoltDetailRepository.findById(id)
                .orElseThrow(() -> new RecoltNotFoundException("RecoltDetail not found"));
        recoltDetailRepository.delete(recoltDetail);
    }



}
