package com.youcode.citronix.service;

import com.youcode.citronix.dto.requestDto.RecoltDetailRequestDto;
import com.youcode.citronix.dto.responseDto.RecoltDetailResponseDto;
import com.youcode.citronix.entity.RecoltDetail;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface RecoltDetailService {

    RecoltDetailResponseDto createRecoltDetail(RecoltDetailRequestDto recoltDetailRequestDto);
    List<RecoltDetailResponseDto> getAllRecoltDetails();
    RecoltDetailResponseDto updateRecoltDetail(Long id, RecoltDetailRequestDto recoltDetailRequestDto);

    RecoltDetailResponseDto getRecoltDetailById(Long id);

    void deleteRecoltDetail(Long id);
}
