package com.youcode.citronix.controller;

import com.youcode.citronix.dto.requestDto.ArbreRequestDto;
import com.youcode.citronix.dto.responseDto.ArbreResponseDto;
import com.youcode.citronix.service.ArbreService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/arbres")
@AllArgsConstructor
public class ArbreController {


    private ArbreService arbreService;

    @PostMapping
    public ResponseEntity<ArbreResponseDto> createArbre(@RequestBody @Valid ArbreRequestDto arbreRequestDto) {
        return new ResponseEntity<>(arbreService.createArbre(arbreRequestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ArbreResponseDto>> getAllArbres() {
        return new ResponseEntity<>(arbreService.getAllArbres(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArbreResponseDto> getArbreById(@PathVariable int id) {
        return new ResponseEntity<>(arbreService.getArbreById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArbreResponseDto> updateArbre(@PathVariable int id, @RequestBody @Valid  ArbreRequestDto arbreRequestDto) {
        return new ResponseEntity<>(arbreService.updateArbre(id, arbreRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArbre(@PathVariable int id) {
        arbreService.deleteArbre(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

