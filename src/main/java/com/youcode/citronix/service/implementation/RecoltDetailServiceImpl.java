package com.youcode.citronix.service.implementation;
import com.youcode.citronix.dto.requestDto.RecoltDetailRequestDto;
import com.youcode.citronix.dto.responseDto.RecoltDetailResponseDto;
import com.youcode.citronix.entity.RecoltDetail;
import com.youcode.citronix.mappers.RecoltDetailMapper;
import com.youcode.citronix.repository.RecoltDetailRepository;
import com.youcode.citronix.repository.RecolteRepository;
import com.youcode.citronix.repository.ArbreRepository;
import com.youcode.citronix.service.RecoltDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecoltDetailServiceImpl implements RecoltDetailService {

    @Autowired
    private RecoltDetailRepository recoltDetailRepository;

    @Autowired
    private RecolteRepository recolteRepository;

    @Autowired
    private ArbreRepository arbreRepository;

    @Autowired
    private RecoltDetailMapper recoltDetailMapper;

    @Override
    public RecoltDetailResponseDto createRecoltDetail(RecoltDetailRequestDto recoltDetailRequestDto) {
        RecoltDetail recoltDetail = recoltDetailMapper.toEntity(recoltDetailRequestDto);
        recoltDetail = recoltDetailRepository.save(recoltDetail);
        return recoltDetailMapper.toResponseDto(recoltDetail);
    }

    @Override
    public List<RecoltDetailResponseDto> getAllRecoltDetails() {
        List<RecoltDetail> recoltDetails = recoltDetailRepository.findAll();
        return recoltDetails.stream()
                .map(recoltDetailMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public RecoltDetailResponseDto getRecoltDetailById(Long id) {
        RecoltDetail recoltDetail = recoltDetailRepository.findById(id).orElseThrow(() -> new RuntimeException("RecoltDetail not found"));
        return recoltDetailMapper.toResponseDto(recoltDetail);
    }

    @Override
    public void deleteRecoltDetail(Long id) {
        RecoltDetail recoltDetail = recoltDetailRepository.findById(id).orElseThrow(() -> new RuntimeException("RecoltDetail not found"));
        recoltDetailRepository.delete(recoltDetail);
    }
}

