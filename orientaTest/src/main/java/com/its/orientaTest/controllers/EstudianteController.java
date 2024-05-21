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

    @GetMapping
    public ResponseEntity<List<EstudianteResponseDTO>> getAllEstudiantes(){
        List<EstudianteResponseDTO> estudiantes = estudianteService.getAllEstudiantes();
        return new ResponseEntity<>(estudiantes, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstudiante(@PathVariable Long id){
        estudianteService.deleteEstudiante(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/autenticar")
    public ResponseEntity<EstudianteResponseDTO> autenticarEstudiante(@RequestBody EstudianteRequestDTO estudianteRequestDTO) {
        // Obtener los datos de correo y contraseña del cuerpo de la solicitud
        String correoElectronico = estudianteRequestDTO.getCorreoElectronico();
        String contrasenia = estudianteRequestDTO.getContrasenia();
        
        // Autentica al estudiante
        EstudianteResponseDTO estudianteAutenticado = estudianteService.autenticarEstudiante(correoElectronico, contrasenia);
        
        // Devuelve los datos del estudiante autenticado
        return ResponseEntity.ok(estudianteAutenticado);
    }
}
