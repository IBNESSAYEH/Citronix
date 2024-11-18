package com.youcode.citronix.controller;

import com.youcode.citronix.dto.requestDto.ChampRequestDto;
import com.youcode.citronix.dto.responseDto.ChampResponseDto;
import com.youcode.citronix.entity.Champ;
import com.youcode.citronix.exception.enums.ErrorMessages;
import com.youcode.citronix.service.ChampService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/champs")
public class ChampController {
    @Autowired
    private ChampService champService;
    @PostMapping
    public ResponseEntity<ChampResponseDto> createChamp(@RequestBody @Valid ChampRequestDto champRequestDto) throws Exception {
        if(champRequestDto.getNom().isBlank() || champRequestDto.getSuperficie() == null || champRequestDto.getFerme().getId().toString().isBlank() ) throw new Exception(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        ChampResponseDto champResponseDto =champService.createChamp(champRequestDto);
        return new ResponseEntity<>(champResponseDto, HttpStatus.CREATED);
    }
}
