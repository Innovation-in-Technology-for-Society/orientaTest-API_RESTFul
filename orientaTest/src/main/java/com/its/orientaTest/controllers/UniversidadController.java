package com.its.orientaTest.controllers;

<<<<<<< HEAD
import java.util.List;

import com.its.orientaTest.model.dto.UniversidadPrecisaResponseDTO;
import com.its.orientaTest.service.UniversidadService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
=======
import com.its.orientaTest.model.dto.UniversidadRequestDTO;
import com.its.orientaTest.model.dto.UniversidadResponseDTO;
import com.its.orientaTest.service.UniversidadService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
>>>>>>> 0e7901caedb59f11eef84d0f5fb2ab371c0b29e2

@RestController
@RequestMapping("/universidades")
@AllArgsConstructor
public class UniversidadController {
    private final UniversidadService universidadService;

<<<<<<< HEAD
    @GetMapping("/beneficio-socioeconomico")
    public ResponseEntity<List<UniversidadPrecisaResponseDTO>> getEconomicBenefitUniversidad(){
        List<UniversidadPrecisaResponseDTO> universidad = universidadService.getUniversidadPrecisa();
        return new ResponseEntity<>(universidad, HttpStatus.OK);
    }
}
=======
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

    @PutMapping("/{nombre}")
    public ResponseEntity<UniversidadResponseDTO> updateUniversidad(@PathVariable String nombre,
                                                                    @Validated @RequestBody UniversidadRequestDTO universidadRequestDTO){
        UniversidadResponseDTO updatedUniversidad = universidadService.updateUniversidad(nombre, universidadRequestDTO);
        return new ResponseEntity<>(updatedUniversidad, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUniversidad(@PathVariable Long id){
        universidadService.deleteUniversidad(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
>>>>>>> 0e7901caedb59f11eef84d0f5fb2ab371c0b29e2
