package com.its.orientaTest.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.its.orientaTest.exceptions.ResourceNotFoundException;
import com.its.orientaTest.mapper.TestMapper;
import com.its.orientaTest.model.dto.TestResponseDTO;
import com.its.orientaTest.model.entities.Test;
import com.its.orientaTest.model.entities.Estudiante;
import java.time.LocalDateTime;
import com.its.orientaTest.repository.TestRepository;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestServiceTest {

    @Mock
    private TestRepository testRepository;

    @Mock
    private TestMapper testMapper;

    @Mock
    private TestPreguntaService testPreguntaService;

    @InjectMocks
    private TestService testService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @org.junit.jupiter.api.Test
    public void testGenerateResultado() {
        // Arrange
        Long testId = 1L;
        Test test = new Test();
        test.setId(testId);
        test.setEstudiante(new Estudiante());
        test.setFechaTest(LocalDateTime.now());

        when(testRepository.findById(testId)).thenReturn(Optional.of(test));
        when(testMapper.toDTO(test)).thenReturn(new TestResponseDTO());

        // Act
        TestResponseDTO result = testService.generateResultado(testId);

        // Assert
        assertNotNull(result);
        verify(testPreguntaService, times(1)).calculateResultado(testId);
        verify(testRepository, times(1)).findById(testId);
        verify(testMapper, times(1)).toDTO(test);
    }
}
