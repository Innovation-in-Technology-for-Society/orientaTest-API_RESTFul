package com.its.orientaTest.service;

import com.its.orientaTest.exceptions.ResourceNotFoundException;
import com.its.orientaTest.mapper.TestMapper;
import com.its.orientaTest.model.dto.TestResponseDTO;
import com.its.orientaTest.repository.TestRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestServiceTest {
    @Mock
    private TestRepository testRepository;

    @Mock
    private TestMapper testMapper;

    @InjectMocks
    private TestService testService;

    @Test
    public void testGetTestByEstudianteId_ReturnsTestResponseDTO() {
        Long estudiante_id = 1L;

        com.its.orientaTest.model.entities.Test test = new com.its.orientaTest.model.entities.Test();
        test.setId(1L);
        // Configurar otros campos de test según sea necesario

        TestResponseDTO testResponseDTO = new TestResponseDTO();
        testResponseDTO.setId(1L);
        // Configurar otros campos de testResponseDTO según sea necesario

        when(testRepository.findByEstudianteId(estudiante_id)).thenReturn(Optional.of(test));
        when(testMapper.toDTO(test)).thenReturn(testResponseDTO);

        TestResponseDTO result = testService.getTestByEstudianteId(estudiante_id);

        assertNotNull(result);
        assertEquals(testResponseDTO.getId(), result.getId());
    }

    @Test
    public void testGetTestByEstudianteId_NonExistingId() {
        Long estudiante_id = 1L;

        when(testRepository.findByEstudianteId(estudiante_id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> testService.getTestByEstudianteId(estudiante_id));
    }
}
