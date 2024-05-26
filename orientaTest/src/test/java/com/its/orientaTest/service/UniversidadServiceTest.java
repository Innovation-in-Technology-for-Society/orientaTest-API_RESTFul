package com.its.orientaTest.service;

import com.its.orientaTest.exceptions.ResourceDuplicateException;
import com.its.orientaTest.exceptions.ResourceNotFoundException;
import com.its.orientaTest.mapper.UniversidadMapper;
import com.its.orientaTest.model.dto.UniversidadRequestDTO;
import com.its.orientaTest.model.dto.UniversidadResponseDTO;
import com.its.orientaTest.model.entities.Universidad;
import com.its.orientaTest.repository.UniversidadRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
public class UniversidadServiceTest {
    @Mock
    private UniversidadRepository universidadRepository;

    @Mock
    private UniversidadMapper universidadMapper;

    @InjectMocks
    private UniversidadService universidadService;

    private UniversidadRequestDTO universidadRequestDTO;
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
}
