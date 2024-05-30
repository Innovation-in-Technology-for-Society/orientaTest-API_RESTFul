package com.its.orientaTest.service;

import com.its.orientaTest.mapper.UniversidadMapper;
import com.its.orientaTest.model.dto.UniversidadPrecisaResponseDTO;
import com.its.orientaTest.repository.UniversidadRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
}
