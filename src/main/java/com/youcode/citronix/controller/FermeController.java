package com.youcode.citronix.controller;

import com.youcode.citronix.dto.requestDto.FermeRequestDto;
import com.youcode.citronix.dto.responseDto.FermeResponseDto;
import com.youcode.citronix.entity.Ferme;
import com.youcode.citronix.exception.enums.ErrorMessages;
import com.youcode.citronix.mappers.FermeMapper;
import com.youcode.citronix.service.FermeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fermes")
public class FermeController {

  @Autowired
  private FermeService fermeService;

  @PostMapping
  public ResponseEntity<FermeResponseDto> createFerme(@RequestBody @Valid FermeRequestDto fermeRequestDto) throws Exception {
    if(fermeRequestDto.getSuperficie() == null) throw new Exception(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
    FermeResponseDto fermeResponseDto = fermeService.createFerme(fermeRequestDto);

    return new ResponseEntity<>(fermeResponseDto, HttpStatus.CREATED);
  }

}
