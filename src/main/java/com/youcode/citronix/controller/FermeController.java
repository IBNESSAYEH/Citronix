package com.youcode.citronix.controller;

import com.youcode.citronix.dto.requestDto.FermeRequestDto;
import com.youcode.citronix.dto.responseDto.FermeResponseDto;
import com.youcode.citronix.entity.Ferme;
import com.youcode.citronix.mappers.FermeMapper;
import com.youcode.citronix.service.FermeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
  public FermeResponseDto createFerme(@RequestBody @Valid FermeRequestDto fermeRequestDto) {
    return fermeService.createFerme(fermeRequestDto);
  }

}
