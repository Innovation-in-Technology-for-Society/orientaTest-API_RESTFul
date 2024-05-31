package com.its.orientaTest.controller;

import java.util.List;

import com.its.orientaTest.model.dto.UniversidadPrecisaResponseDTO;
import com.its.orientaTest.service.UniversidadService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/universidades")
@AllArgsConstructor
public class UniversidadController {
    private final UniversidadService universidadService;

    @GetMapping("/beneficio-socioeconomico")
    public ResponseEntity<List<UniversidadPrecisaResponseDTO>> getEconomicBenefitUniversidad(){
        List<UniversidadPrecisaResponseDTO> universidad = universidadService.getUniversidadPrecisa();
        return new ResponseEntity<>(universidad, HttpStatus.OK);
    }
}