package com.its.orientaTest.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

import com.its.orientaTest.exceptions.ResourceDuplicateException;
import com.its.orientaTest.exceptions.ResourceNotFoundException;
import com.its.orientaTest.mapper.CarreraMapper;
import com.its.orientaTest.model.dto.CarreraRequestDTO;
import com.its.orientaTest.model.dto.CarreraResponseDTO;
import com.its.orientaTest.model.entities.Carrera;
import com.its.orientaTest.repository.CarreraRepository;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Collections;

@ExtendWith(MockitoExtension.class)
public class CarreraServiceTest {
    @Mock
    private CarreraRepository carreraRepository;

    @Mock
    private CarreraMapper carreraMapper;

    @InjectMocks
    private CarreraService carreraService;
    @Test
    public void testGetAllCarreras() {
        // Arrange
        Carrera carrera1 = new Carrera();
        carrera1.setId(1L);
        Carrera carrera2 = new Carrera();
        carrera2.setId(2L);
        List<Carrera> carreraList = Arrays.asList(carrera1, carrera2);

        when(carreraRepository.findAll()).thenReturn(carreraList);

        CarreraResponseDTO responseDTO1 = new CarreraResponseDTO(carrera1.getNombre(), carrera1.getDescripcion());
        CarreraResponseDTO responseDTO2 = new CarreraResponseDTO(carrera2.getNombre(), carrera2.getDescripcion());

        List<CarreraResponseDTO> expectedResponse = Arrays.asList(responseDTO1, responseDTO2);
        when(carreraMapper.toListDTO(carreraList)).thenReturn(expectedResponse);

        // Act
        List<CarreraResponseDTO> result = carreraService.getAllCarreras();

        // Assert
        assertNotNull(result);
        assertEquals(expectedResponse.size(), result.size());

        verify(carreraRepository, times(1)).findAll();
        verify(carreraMapper, times(1)).toListDTO(carreraList);
    }

    @Test
    public void testGetAllCarreras_NoCarrerasFound() {
        // Arrange
        when(carreraRepository.findAll()).thenReturn(Collections.emptyList());
        when(carreraMapper.toListDTO(anyList())).thenReturn(Collections.emptyList());

        // Act
        List<CarreraResponseDTO> result = carreraService.getAllCarreras();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(carreraRepository, times(1)).findAll();
        verify(carreraMapper, times(1)).toListDTO(anyList());
    }
}
