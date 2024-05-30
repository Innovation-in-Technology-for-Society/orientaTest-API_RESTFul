package com.its.orientaTest.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import com.its.orientaTest.model.dto.TestPreguntaRequestDTO;
import com.its.orientaTest.model.dto.TestPreguntaResponseDTO;
import com.its.orientaTest.model.entities.Pregunta;
import com.its.orientaTest.model.entities.TestPregunta;
import com.its.orientaTest.repository.PreguntaRepository;
import com.its.orientaTest.repository.TestPreguntaRepository;
import com.its.orientaTest.repository.TestRepository;
import com.its.orientaTest.mapper.TestPreguntaMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestPreguntaServiceTest {

    @Mock
    private TestPreguntaMapper testPreguntaMapper;

    @Mock
    private TestRepository testRepository;

    @Mock
    private PreguntaRepository preguntaRepository;

    @Mock
    private TestPreguntaRepository testPreguntaRepository;

    @InjectMocks
    private TestPreguntaService testPreguntaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetPreguntasVocacionales() {
        // Arrange
        Long test_id = 1L;
        List<Pregunta> preguntas = new ArrayList<>();
        preguntas.add(new Pregunta());
        preguntas.add(new Pregunta());

        when(preguntaRepository.findVocacional()).thenReturn(preguntas);
        when(testRepository.findById(test_id)).thenReturn(java.util.Optional.of(new com.its.orientaTest.model.entities.Test()));

        // Act
        assertDoesNotThrow(() -> testPreguntaService.getPreguntasVocacionales(test_id));
    }

    @Test
    public void testGetPreguntasAutoPercepcion() {
        // Arrange
        Long test_id = 1L;
        List<Pregunta> preguntas = new ArrayList<>();
        preguntas.add(new Pregunta());
        preguntas.add(new Pregunta());

        when(preguntaRepository.findAutoPercepcion()).thenReturn(preguntas);
        when(testRepository.findById(test_id)).thenReturn(java.util.Optional.of(new com.its.orientaTest.model.entities.Test()));

        // Act
        assertDoesNotThrow(() -> testPreguntaService.getPreguntasAutoPercepcion(test_id));
    }

    @Test
    public void testSavePreguntas() {
        // Arrange
        Long test_id = 1L;
        List<Pregunta> preguntas = new ArrayList<>();
        preguntas.add(new Pregunta());
        preguntas.add(new Pregunta());

        com.its.orientaTest.model.entities.Test test = new com.its.orientaTest.model.entities.Test();
        test.setId(test_id);

        when(testRepository.findById(test_id)).thenReturn(java.util.Optional.of(test));
        when(testPreguntaRepository.save(any(TestPregunta.class))).thenReturn(new TestPregunta());

        // Act
        assertDoesNotThrow(() -> testPreguntaService.savePreguntas(test_id, preguntas, "test_type"));
    }

    @Test
    public void testAnswerPregunta() {
        // Arrange
        Long test_id = 1L;
        Long pregunta_id = 1L;
        TestPreguntaRequestDTO requestDTO = new TestPreguntaRequestDTO();
        requestDTO.setValor(5);

        com.its.orientaTest.model.entities.Test test = new com.its.orientaTest.model.entities.Test();
        test.setId(test_id);

        TestPregunta testPregunta = new TestPregunta();
        testPregunta.setId(pregunta_id);

        when(testRepository.findById(test_id)).thenReturn(java.util.Optional.of(test));
        when(testPreguntaRepository.findById(pregunta_id)).thenReturn(java.util.Optional.of(testPregunta));
        when(testPreguntaRepository.save(any(TestPregunta.class))).thenReturn(new TestPregunta());

        // Act
        TestPreguntaResponseDTO result = testPreguntaService.answerPregunta(test_id, pregunta_id, requestDTO);

        // Assert
        assertNotNull(result);
        assertEquals(requestDTO.getValor(), result.getValor());
    }
}