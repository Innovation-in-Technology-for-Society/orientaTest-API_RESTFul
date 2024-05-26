package com.its.orientaTest.service;
import com.its.orientaTest.exceptions.ResourceNotFoundException;
import com.its.orientaTest.mapper.EstudianteMapper;
import com.its.orientaTest.model.dto.EstudianteRequestDTO;
import com.its.orientaTest.model.dto.EstudianteResponseDTO;
import com.its.orientaTest.model.entities.Estudiante;
import com.its.orientaTest.repository.EstudianteRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EstudianteServiceTest {

    @Mock
    private EstudianteRepository estudianteRepository;

    @Mock
    private EstudianteMapper estudianteMapper;

    @InjectMocks
    private EstudianteService estudianteService;

    private Estudiante estudiante;
    private EstudianteRequestDTO estudianteRequestDTO;
    private EstudianteResponseDTO estudianteResponseDTO;

    @BeforeEach
    void setUp() {
        estudiante = new Estudiante(1L, "Juan", "Pérez", "juan.perez@example.com", "password", "123456789", "Dirección 123", LocalDateTime.now(), 0);
        estudianteRequestDTO = new EstudianteRequestDTO("Juan", "Pérez", "juan.perez@example.com", "123456789", "Dirección 123", "password");
        estudianteResponseDTO = new EstudianteResponseDTO(1L, "Juan", "Pérez", "juan.perez@example.com", "123456789", "Dirección 123", LocalDateTime.now(), 0);
    }

    @Test
    void testCreateEstudiante() {
        when(estudianteMapper.toEntity(any(EstudianteRequestDTO.class))).thenReturn(estudiante);
        when(estudianteRepository.save(any(Estudiante.class))).thenReturn(estudiante);
        when(estudianteMapper.toDTO(any(Estudiante.class))).thenReturn(estudianteResponseDTO);

        EstudianteResponseDTO responseDTO = estudianteService.createEstudiante(estudianteRequestDTO);

        assertEquals(estudianteRequestDTO.getNombre(), responseDTO.getNombre());
        verify(estudianteRepository, times(1)).save(any(Estudiante.class));
    }

    @Test
    void testGetAllEstudiantes() {
        when(estudianteRepository.findAll()).thenReturn(Collections.singletonList(estudiante));
        when(estudianteMapper.toListDTO(anyList())).thenReturn(Collections.singletonList(estudianteResponseDTO));

        List<EstudianteResponseDTO> responseDTOs = estudianteService.getAllEstudiantes();

        assertEquals(1, responseDTOs.size());
        verify(estudianteRepository, times(1)).findAll();
    }

    @Test
    void testAutenticarEstudiante() {
        when(estudianteRepository.findByCorreoElectronico(anyString())).thenReturn(Optional.of(estudiante));
        when(estudianteMapper.toDTO(any(Estudiante.class))).thenReturn(estudianteResponseDTO);

        EstudianteResponseDTO responseDTO = estudianteService.autenticarEstudiante("juan.perez@example.com", "password");

        assertEquals(estudiante.getCorreoElectronico(), responseDTO.getCorreoElectronico());
        verify(estudianteRepository, times(1)).findByCorreoElectronico(anyString());
    }

    @Test
    void testAutenticarEstudiante_NotFound() {
        when(estudianteRepository.findByCorreoElectronico(anyString())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> estudianteService.autenticarEstudiante("juan.perez@example.com", "password"));
    }

    @Test
    void testActualizarEstudiante() {
        when(estudianteRepository.findById(anyLong())).thenReturn(Optional.of(estudiante));
        when(estudianteRepository.save(any(Estudiante.class))).thenReturn(estudiante);
        when(estudianteMapper.toDTO(any(Estudiante.class))).thenReturn(estudianteResponseDTO);

        EstudianteResponseDTO responseDTO = estudianteService.actualizarEstudiante(1L, estudianteRequestDTO);

        assertEquals(estudianteRequestDTO.getNombre(), responseDTO.getNombre());
        verify(estudianteRepository, times(1)).findById(anyLong());
        verify(estudianteRepository, times(1)).save(any(Estudiante.class));
    }

    @Test
    void testActualizarEstudiante_NotFound() {
        when(estudianteRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> estudianteService.actualizarEstudiante(1L, estudianteRequestDTO));
    }
}