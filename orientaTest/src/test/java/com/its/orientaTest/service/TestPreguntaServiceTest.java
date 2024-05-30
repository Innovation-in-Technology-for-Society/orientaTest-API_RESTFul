package com.its.orientaTest.service;

import com.its.orientaTest.mapper.TestPreguntaMapper;
import com.its.orientaTest.model.dto.TestPreguntaResponseDTO;
import com.its.orientaTest.repository.TestPreguntaRepository;
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
public class TestPreguntaServiceTest {
    @Mock
    private TestPreguntaRepository testPreguntaRepository;

    @Mock
    private TestPreguntaMapper testPreguntaMapper;

    @InjectMocks
    private TestPreguntaService testPreguntaService;

    @Test
    public void testGetResultadosTipoTest_ReturnsEmptyList() {
        Long test_id = 1L;
        String tipoTest = "auto-percepcion";

        when(testPreguntaRepository.findByTestIdAndTipoTest(test_id, tipoTest)).thenReturn(Collections.emptyList());

        List<TestPreguntaResponseDTO> result = testPreguntaService.getResultadosTipoTest(test_id, tipoTest);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
