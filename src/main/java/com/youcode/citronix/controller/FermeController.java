package com.youcode.citronix.controller;

import com.youcode.citronix.dto.requestDto.FermeRequestDto;
import com.youcode.citronix.dto.responseDto.FermeResponseDto;
import com.youcode.citronix.entity.Ferme;
import com.youcode.citronix.exception.FermeException;
import com.youcode.citronix.exception.enums.ErrorMessages;
import com.youcode.citronix.mappers.FermeMapper;
import com.youcode.citronix.service.FermeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fermes")
public class FermeController {

  @Autowired
  private FermeService fermeService;

  @PostMapping
  public ResponseEntity<FermeResponseDto> createFerme(@RequestBody @Valid FermeRequestDto fermeRequestDto) throws FermeException {
    if(fermeRequestDto.getSuperficie() == null || fermeRequestDto.getNom().isBlank() || fermeRequestDto.getLocalisation().isBlank()) throw new FermeException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
    FermeResponseDto fermeResponseDto = fermeService.createFerme(fermeRequestDto);
    return new ResponseEntity<>(fermeResponseDto, HttpStatus.CREATED);
  }


  @GetMapping
  public ResponseEntity<List<FermeResponseDto>> getAllFermes() {
    return new ResponseEntity<>(fermeService.getAllFermes(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<FermeResponseDto> getFermeById(@PathVariable long id) {
    return new ResponseEntity<>(fermeService.getFermeById(id), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<FermeResponseDto> updateFerme(@PathVariable long id, @RequestBody FermeRequestDto fermeRequestDto) {
    return new ResponseEntity<>(fermeService.updateFerme(id, fermeRequestDto), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteFerme(@PathVariable long id) {
    fermeService.deleteFerme(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
