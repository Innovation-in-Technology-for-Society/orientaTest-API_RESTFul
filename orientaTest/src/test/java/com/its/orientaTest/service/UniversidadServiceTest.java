package com.its.orientaTest.service;

import com.its.orientaTest.exceptions.ResourceNotFoundException;
import com.its.orientaTest.mapper.UniversidadMapper;
import com.its.orientaTest.model.entities.Universidad;
import com.its.orientaTest.model.dto.UniversidadResponseDTO;
import com.its.orientaTest.repository.UniversidadRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UniversidadServiceTest {   
    @Mock
    private UniversidadRepository universidadRepository;
    @Mock
    private UniversidadMapper universidadMapper;

    @InjectMocks
    private UniversidadService universidadService;
    
    @Test
    public void testGetUniversidadByNombre_ExistingName(){
        // Arrange
        String nombre = "Universidad Peruana de Ciencias Aplicadas";
        Universidad universidad = new Universidad();
        when (universidadRepository.findByNombre(nombre)).thenReturn(Optional.of(universidad));
        
        //Mocking mapper
        UniversidadResponseDTO responseDTO = new UniversidadResponseDTO();
        responseDTO.setNombre(nombre);
        when(universidadMapper.toDTO(universidad)).thenReturn(responseDTO);

        // Act
        UniversidadResponseDTO result = universidadService.getUniversidadByNombre(nombre);

        // Assert
        assertNotNull(result);
        assertEquals(nombre, result.getNombre());
    }
}   
