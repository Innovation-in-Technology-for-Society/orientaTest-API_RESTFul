package com.its.orientaTest.service;

import com.its.orientaTest.mapper.UniversidadMapper;
import com.its.orientaTest.model.dto.UniversidadPrecisaResponseDTO;
import com.its.orientaTest.model.entities.Universidad;
import com.its.orientaTest.repository.UniversidadRepository;
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

@ExtendWith(MockitoExtension.class)
public class UniversidadServiceTest {
    @Mock
    private UniversidadRepository universidadRepository;

    @Mock
    private UniversidadMapper universidadMapper;

    @InjectMocks
    private UniversidadService universidadService;

    @Test
    public void testGetUniversidadAll_ReturnsEmptyList() {
        when(universidadRepository.findAll()).thenReturn(Collections.emptyList());
        when(universidadMapper.toListDTOPrecisa(Collections.emptyList())).thenReturn(Collections.emptyList());

        List<UniversidadPrecisaResponseDTO> result = universidadService.getUniversidadPrecisa();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetUniversidadAll_ReturnsListOfDtos() {
        Universidad universidad1 = new Universidad();
        universidad1.setId(1L);
        universidad1.setNombre("Universidad 1");

        Universidad universidad2 = new Universidad();
        universidad2.setId(2L);
        universidad2.setNombre("Universidad 2");

        List<Universidad> universidades = Arrays.asList(universidad1, universidad2);

        when(universidadRepository.findAll()).thenReturn(universidades);

        UniversidadPrecisaResponseDTO dto1 = new UniversidadPrecisaResponseDTO();
        dto1.setId(1L);
        dto1.setNombre("Universidad 1");

        UniversidadPrecisaResponseDTO dto2 = new UniversidadPrecisaResponseDTO();
        dto2.setId(2L);
        dto2.setNombre("Universidad 2");

        when(universidadMapper.toListDTOPrecisa(universidades)).thenReturn(Arrays.asList(dto1, dto2));

        List<UniversidadPrecisaResponseDTO> result = universidadService.getUniversidadPrecisa();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(dto1, result.get(0));
        assertEquals(dto2, result.get(1));
    }
}
