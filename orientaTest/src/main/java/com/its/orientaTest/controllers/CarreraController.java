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

    @GetMapping
    public ResponseEntity<List<CarreraResponseDTO>> getAllCarreras(){
        List<CarreraResponseDTO> carreras = carreraService.getAllCarreras();
        return new ResponseEntity<>(carreras, HttpStatus.OK);
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<CarreraResponseDTO> getCarreraByNombre(@PathVariable String nombre){
        CarreraResponseDTO carrera = carreraService.getCarreraByNombre(nombre);
        return new ResponseEntity<>(carrera, HttpStatus.OK);
    }

    @PutMapping("/{nombre}")
    public ResponseEntity<CarreraResponseDTO> updateCarrera(@PathVariable String nombre,
                                                            @Validated @RequestBody CarreraRequestDTO carreraRequestDTO){
        CarreraResponseDTO updatedCarrera = carreraService.updateCarrera(nombre, carreraRequestDTO);
        return new ResponseEntity<>(updatedCarrera, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarrera(@PathVariable Long id){
        carreraService.deleteCarrera(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
