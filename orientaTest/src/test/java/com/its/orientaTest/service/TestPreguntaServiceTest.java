package com.its.orientaTest.service;

import com.its.orientaTest.mapper.TestPreguntaMapper;
import com.its.orientaTest.model.dto.PreguntaResponseDTO;
import com.its.orientaTest.model.dto.TestPreguntaResponseDTO;
import com.its.orientaTest.model.entities.Pregunta;
import com.its.orientaTest.model.entities.TestPregunta;
import com.its.orientaTest.repository.TestPreguntaRepository;
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
class TestPreguntaServiceTest {
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

    @Test
    public void testGetResultadosTipoTest_ReturnsListOfDtos() {
        Long test_id = 1L;
        String tipoTest = "auto-percepcion";


        Pregunta pregunta1 = new Pregunta();
        pregunta1.setId(1L);
        pregunta1.setEnunciado("Enunciado 1");

        TestPregunta testPregunta1 = new TestPregunta();
        testPregunta1.setPregunta(pregunta1);


        Pregunta pregunta2 = new Pregunta();
        pregunta2.setId(2L);
        pregunta2.setEnunciado("Enunciado 2");

        TestPregunta testPregunta2 = new TestPregunta();
        testPregunta2.setPregunta(pregunta2);

        List<TestPregunta> testPreguntas = Arrays.asList(testPregunta1, testPregunta2);

        when(testPreguntaRepository.findByTestIdAndTipoTest(test_id, tipoTest)).thenReturn(testPreguntas);

        TestPreguntaResponseDTO dto1 = new TestPreguntaResponseDTO();
        PreguntaResponseDTO preguntaDTO1 = new PreguntaResponseDTO();
        preguntaDTO1.setId(1L);
        preguntaDTO1.setEnunciado("¿Eres consciente de tus responsabilidades y cumples con tus tareas de manera puntual y organizada?");
        dto1.setPregunta_id(preguntaDTO1);

        TestPreguntaResponseDTO dto2 = new TestPreguntaResponseDTO();
        PreguntaResponseDTO preguntaDTO2 = new PreguntaResponseDTO();
        preguntaDTO2.setId(2L);
        preguntaDTO2.setEnunciado("¿Crees que tienes las habilidades y conocimientos necesarios para conseguir y mantener un empleo en tu área de estudio?");
        dto2.setPregunta_id(preguntaDTO2);

        when(testPreguntaMapper.toResponseDTO(testPregunta1)).thenReturn(dto1);
        when(testPreguntaMapper.toResponseDTO(testPregunta2)).thenReturn(dto2);

        List<TestPreguntaResponseDTO> result = testPreguntaService.getResultadosTipoTest(test_id, tipoTest);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(dto1, result.get(0));
        assertEquals(dto2, result.get(1));
    }
}