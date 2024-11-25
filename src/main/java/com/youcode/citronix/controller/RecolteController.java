package com.youcode.citronix.controller;

import com.youcode.citronix.dto.requestDto.RecolteRequestDto;
import com.youcode.citronix.dto.responseDto.RecolteResponseDto;
import com.youcode.citronix.service.RecoltDetailService;
import com.youcode.citronix.service.RecolteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recoltes")
@AllArgsConstructor
public class RecolteController {

    private RecolteService recolteService;

    @PostMapping
    public ResponseEntity<RecolteResponseDto> createRecolte(@RequestBody @Valid  RecolteRequestDto recolteRequestDto) {
        return new ResponseEntity<>(recolteService.createRecolte(recolteRequestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RecolteResponseDto>> getAllRecoltes() {
        return new ResponseEntity<>(recolteService.getAllRecoltes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecolteResponseDto> getRecolteById(@PathVariable Long id) {
        return new ResponseEntity<>(recolteService.getRecolteById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecolteResponseDto> updateRecolte(@PathVariable Long id, @RequestBody @Valid  RecolteRequestDto recolteRequestDto) {
        return new ResponseEntity<>(recolteService.updateRecolte(id, recolteRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecolte(@PathVariable Long id) {
        recolteService.deleteRecolte(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

