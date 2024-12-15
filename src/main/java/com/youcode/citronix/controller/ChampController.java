package com.youcode.citronix.controller;

import com.youcode.citronix.dto.requestDto.ChampRequestDto;
import com.youcode.citronix.dto.responseDto.ChampResponseDto;
import com.youcode.citronix.entity.Champ;
import com.youcode.citronix.exception.enums.ErrorMessages;
import com.youcode.citronix.service.ChampService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/champs")
@AllArgsConstructor
public class ChampController {

    private ChampService champService;
    @PostMapping
    public ResponseEntity<ChampResponseDto> createChamp(@RequestBody @Valid ChampRequestDto champRequestDto) throws Exception {
        if(champRequestDto.getNom().isBlank() || champRequestDto.getSuperficie() == null  ) throw new Exception(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
         ChampResponseDto champResponseDto =champService.createChamp(champRequestDto);
        return new ResponseEntity<>(champResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ChampResponseDto>> getAllChamps() {
        return new ResponseEntity<>(champService.getAllChamps(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChampResponseDto> getChampById(@PathVariable long id) {
        return new ResponseEntity<>(champService.getChampById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChampResponseDto> updateChamp(@PathVariable long id, @RequestBody @Valid  ChampRequestDto champRequestDto) {
        return new ResponseEntity<>(champService.updateChamp(id, champRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChamp(@PathVariable long id) {
        champService.deleteChamp(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
