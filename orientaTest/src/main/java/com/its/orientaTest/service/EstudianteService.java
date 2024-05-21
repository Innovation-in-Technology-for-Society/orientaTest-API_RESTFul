package com.its.orientaTest.service;

import com.its.orientaTest.exceptions.ResourceNotFoundException;
import com.its.orientaTest.exceptions.BadRequestException;
import com.its.orientaTest.mapper.EstudianteMapper;
import com.its.orientaTest.model.dto.EstudianteRequestDTO;
import com.its.orientaTest.model.dto.EstudianteResponseDTO;
import com.its.orientaTest.model.entities.Estudiante;
import com.its.orientaTest.repository.EstudianteRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class EstudianteService {
    private final EstudianteRepository estudianteRepository;
    private final EstudianteMapper estudianteMapper;

    @Transactional
    public EstudianteResponseDTO createEstudiante(EstudianteRequestDTO estudianteRequestDTO){
        Estudiante estudiante = estudianteMapper.toEntity(estudianteRequestDTO);

        estudiante.setFechaRegistro(LocalDateTime.now());
        estudiante.setIntentosTest(0);

        estudianteRepository.save(estudiante);
        return estudianteMapper.toDTO(estudiante);
    }
    @Transactional(readOnly = true)
    public List<EstudianteResponseDTO> getAllEstudiantes(){
        List<Estudiante> estudiantes = estudianteRepository.findAll();
        return estudianteMapper.toListDTO(estudiantes);
    }

    @Transactional(readOnly = true)
    public EstudianteResponseDTO getEstudianteByNombreApellido(String nombre, String apellido){
        Estudiante estudiante = estudianteRepository.findByNombreApellido(nombre, apellido)
        .orElseThrow(() -> new ResourceNotFoundException("Estudiante nombre:" + nombre + "apellido:" + apellido + "no encontrado"));
        return estudianteMapper.toDTO(estudiante);
    }
}
