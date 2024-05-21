package com.its.orientaTest.controllers;


import com.its.orientaTest.model.dto.EstudianteRequestDTO;
import com.its.orientaTest.model.dto.EstudianteResponseDTO;
import com.its.orientaTest.service.EstudianteService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiantes")
@AllArgsConstructor
public class EstudianteController {

    private final EstudianteService estudianteService;

    @PostMapping
    public ResponseEntity<EstudianteResponseDTO> createEstudiante(@Validated @RequestBody EstudianteRequestDTO estudianteRequestDTO){
        EstudianteResponseDTO createdEstudiante = estudianteService.createEstudiante(estudianteRequestDTO);
        return new ResponseEntity<>(createdEstudiante, HttpStatus.CREATED);
    }
}
