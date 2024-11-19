package com.youcode.citronix.controller;

import com.youcode.citronix.dto.requestDto.RecoltDetailRequestDto;
import com.youcode.citronix.dto.responseDto.RecoltDetailResponseDto;
import com.youcode.citronix.service.RecoltDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recoltDetails")
public class RecoltDetailController {

    @Autowired
    private RecoltDetailService recoltDetailService;

    @PostMapping
    public ResponseEntity<RecoltDetailResponseDto> createRecoltDetail(@RequestBody RecoltDetailRequestDto recoltDetailRequestDto) {
        return new ResponseEntity<>(recoltDetailService.createRecoltDetail(recoltDetailRequestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RecoltDetailResponseDto>> getAllRecoltDetails() {
        return new ResponseEntity<>(recoltDetailService.getAllRecoltDetails(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecoltDetailResponseDto> getRecoltDetailById(@PathVariable Long id) {
        return new ResponseEntity<>(recoltDetailService.getRecoltDetailById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecoltDetail(@PathVariable Long id) {
        recoltDetailService.deleteRecoltDetail(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}