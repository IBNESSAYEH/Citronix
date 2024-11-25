package com.youcode.citronix.controller;

import com.youcode.citronix.dto.requestDto.VenteRequestDto;
import com.youcode.citronix.dto.responseDto.VenteResponseDto;
import com.youcode.citronix.service.VenteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/ventes")
@AllArgsConstructor
public class VenteController {


    private VenteService venteService;

    @PostMapping
    public ResponseEntity<VenteResponseDto> createVente(@RequestBody @Valid VenteRequestDto venteRequestDto) {
        return new ResponseEntity<>(venteService.createVente(venteRequestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<VenteResponseDto>> getAllVentes() {
        return new ResponseEntity<>(venteService.getAllVentes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VenteResponseDto> getVenteById(@PathVariable Long id) {
        return new ResponseEntity<>(venteService.getVenteById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VenteResponseDto> updateVente(@PathVariable Long id, @RequestBody @Valid VenteRequestDto venteRequestDto) {
        return new ResponseEntity<>(venteService.updateVente(id, venteRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVente(@PathVariable Long id) {
        venteService.deleteVente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
