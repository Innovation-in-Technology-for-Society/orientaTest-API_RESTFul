package com.its.orientaTest.controllers;

import com.its.orientaTest.model.dto.CarreraRequestDTO;
import com.its.orientaTest.model.dto.CarreraResponseDTO;
import com.its.orientaTest.service.CarreraService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carreras")
@AllArgsConstructor
public class CarreraController {
    private final CarreraService carreraService;

    @PostMapping
    public ResponseEntity<CarreraResponseDTO> createCarrera(@Validated @RequestBody CarreraRequestDTO carreraRequestDTO){
        CarreraResponseDTO createdCarrera = carreraService.createCarrera(carreraRequestDTO);
        return new ResponseEntity<>(createdCarrera, HttpStatus.CREATED);
    }
}
