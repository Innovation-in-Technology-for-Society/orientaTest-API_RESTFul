package com.its.orientaTest.controllers;

import com.its.orientaTest.model.dto.UniversidadResponseDTO;
import com.its.orientaTest.service.UniversidadService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/universidades")
@AllArgsConstructor
public class UniversidadController {
    private final UniversidadService universidadService;

    @GetMapping("/{nombre}")
    public ResponseEntity<UniversidadResponseDTO> getUniversidadByNombre(@PathVariable String nombre){
        UniversidadResponseDTO universidad = universidadService.getUniversidadByNombre(nombre);
        return new ResponseEntity<>(universidad, HttpStatus.OK);
    }
}
