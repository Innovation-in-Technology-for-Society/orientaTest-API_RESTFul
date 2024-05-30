package com.its.orientaTest.service;

import com.its.orientaTest.exceptions.ResourceNotFoundException;
import com.its.orientaTest.mapper.PagoMapper;
import com.its.orientaTest.model.dto.PagoBeneficioResponseDTO;
import com.its.orientaTest.model.entities.Pago;
import com.its.orientaTest.repository.EstudianteRepository;
import com.its.orientaTest.repository.PagoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
public class PagoServiceTest {
    @Mock
    private PagoMapper pagoMapper;

    @Mock
    private PagoRepository pagoRepository;

    @Mock
    private EstudianteRepository estudianteRepository;

    @InjectMocks
    private PagoService pagoService;

    @Test
    public void testGetPagosBeneficioByEstudianteId_EstudianteNoEncontrado() {
        Long idEstudiante = 3L;

        when(estudianteRepository.existsById(idEstudiante)).thenReturn(false);

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            pagoService.getPagosBeneficioByEstudianteId(idEstudiante);
        });

        assertEquals("Estudiante no encontrado", exception.getMessage());
    }

    @Test
    public void testGetPagosBeneficioByEstudianteId_ReturnsEmptyList() {
        Long idEstudiante = 1L;

        when(estudianteRepository.existsById(idEstudiante)).thenReturn(true);
        when(pagoRepository.findByIdEstudiante(idEstudiante)).thenReturn(Collections.emptyList());

        when(pagoMapper.toListBeneficioDTO(Collections.emptyList())).thenReturn(Collections.emptyList());

        List<PagoBeneficioResponseDTO> result = pagoService.getPagosBeneficioByEstudianteId(idEstudiante);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
