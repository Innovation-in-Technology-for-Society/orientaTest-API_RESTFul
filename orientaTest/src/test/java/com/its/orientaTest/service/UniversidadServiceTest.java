package com.its.orientaTest.service;

import com.its.orientaTest.exceptions.ResourceNotFoundException;
import com.its.orientaTest.mapper.UniversidadMapper;
import com.its.orientaTest.model.dto.UniversidadResponseDTO;
import com.its.orientaTest.model.entities.Universidad;
import com.its.orientaTest.repository.UniversidadRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UniversidadServiceTest {
    @Mock
    private UniversidadRepository universidadRepository;
  
    @Mock
    private UniversidadMapper universidadMapper;

    @InjectMocks
    private UniversidadService universidadService;

    private Universidad universidad;
    private UniversidadResponseDTO universidadResponseDTO;

    @Test
    void testGetAllUniversidades() {
        when(universidadRepository.findAll()).thenReturn(Collections.singletonList(universidad));
        when(universidadMapper.toListDTO(anyList())).thenReturn(Collections.singletonList(universidadResponseDTO));

        List<UniversidadResponseDTO> responseDTOList = universidadService.getAllUniversidades();

        assertNotNull(responseDTOList);
        assertFalse(responseDTOList.isEmpty());
        assertEquals(1, responseDTOList.size());

        UniversidadResponseDTO responseDTO = responseDTOList.get(0);
        assertEquals("Universidad XYZ", responseDTO.getNombre());
        assertEquals("xyz@example.com", responseDTO.getCorreoElectronico());
        assertEquals("123456789", responseDTO.getTelefono());
        assertEquals("Ubicación XYZ", responseDTO.getUbicacion());
        assertEquals(5, responseDTO.getRanking());

        verify(universidadRepository, times(1)).findAll();
    }

    @Test
    void testGetAllUniversidades_NoUniversidadesFound() {
        // Arrange
        when(universidadRepository.findAll()).thenReturn(Collections.emptyList());
        when(universidadMapper.toListDTO(anyList())).thenReturn(Collections.emptyList());

        // Act
        List<UniversidadResponseDTO> responseDTOList = universidadService.getAllUniversidades();

        // Assert
        assertNotNull(responseDTOList);
        assertTrue(responseDTOList.isEmpty());

        verify(universidadRepository, times(1)).findAll();
        verify(universidadMapper, times(1)).toListDTO(anyList());
    }

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

    @Test
    public void testGetUniversidadByNombre_NonExistingName(){
        // Arrange
        String nombre = "Universidad Tecnológica del Perú";
        when (universidadRepository.findByNombre(nombre)).thenReturn(Optional.empty()); 
        
        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> universidadService.getUniversidadByNombre(nombre));
    }
}
