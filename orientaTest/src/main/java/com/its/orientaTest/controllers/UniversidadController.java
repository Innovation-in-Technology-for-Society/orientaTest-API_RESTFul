package com.its.orientaTest.controllers;

import com.its.orientaTest.model.dto.UniversidadRequestDTO;
import com.its.orientaTest.model.dto.UniversidadResponseDTO;
import com.its.orientaTest.service.UniversidadService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/universidades")
@AllArgsConstructor
public class UniversidadController {
    private final UniversidadService universidadService;
    
    @PostMapping
    public ResponseEntity<UniversidadResponseDTO> createUniversidad(@Validated @RequestBody UniversidadRequestDTO universidadRequestDTO){
        UniversidadResponseDTO createdUniversidad = universidadService.createUniversidad(universidadRequestDTO);
        return new ResponseEntity<>(createdUniversidad, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UniversidadResponseDTO>> getAllUniversidades(){
        List<UniversidadResponseDTO> universidades = universidadService.getAllUniversidades();
        return new ResponseEntity<>(universidades, HttpStatus.OK);
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<UniversidadResponseDTO> getUniversidadByNombre(@PathVariable String nombre){
        UniversidadResponseDTO universidad = universidadService.getUniversidadByNombre(nombre);
        return new ResponseEntity<>(universidad, HttpStatus.OK);
    }
}
