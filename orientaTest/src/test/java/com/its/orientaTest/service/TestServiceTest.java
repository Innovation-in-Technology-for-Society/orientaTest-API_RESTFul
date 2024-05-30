package com.its.orientaTest.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.its.orientaTest.exceptions.ResourceNotFoundException;
import com.its.orientaTest.mapper.TestMapper;
import com.its.orientaTest.model.dto.TestRequestDTO;
import com.its.orientaTest.model.dto.TestResponseDTO;
import com.its.orientaTest.model.entities.Estudiante;
import com.its.orientaTest.repository.EstudianteRepository;
import com.its.orientaTest.repository.TestRepository;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TestServiceTest {
    @Mock
    private TestRepository testRepository;

    @Mock
    private TestMapper testMapper;

    @Mock
    private EstudianteRepository estudianteRepository;

    @Mock
    private TestPreguntaService testPreguntaService;

    @InjectMocks
    private TestService testService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @org.junit.jupiter.api.Test
    public void testStartTest() {
        // Arrange
        TestRequestDTO requestDTO = new TestRequestDTO();
        requestDTO.setEstudiante_id(1L);

        Estudiante estudiante = new Estudiante();
        estudiante.setId(1L);
        estudiante.setIntentosTest(0);

        com.its.orientaTest.model.entities.Test test = new com.its.orientaTest.model.entities.Test();
        test.setId(1L);
        test.setFechaTest(LocalDateTime.now());
        test.setEstudiante(estudiante);

        when(estudianteRepository.findById(1L)).thenReturn(Optional.of(estudiante));
        when(testMapper.toEntity(requestDTO)).thenReturn(test);
        when(testRepository.save(any(com.its.orientaTest.model.entities.Test.class))).thenReturn(test);
        when(testMapper.toDTO(any(com.its.orientaTest.model.entities.Test.class))).thenReturn(new TestResponseDTO());

        // Act
        TestResponseDTO result = testService.startTest(requestDTO);

        // Assert
        assertNotNull(result);
        verify(estudianteRepository, times(1)).findById(1L);
        verify(testMapper, times(1)).toEntity(requestDTO);
        verify(testRepository, times(1)).save(any(com.its.orientaTest.model.entities.Test.class));
        verify(testMapper, times(1)).toDTO(any(com.its.orientaTest.model.entities.Test.class));
        verify(testPreguntaService, times(1)).getPreguntasVocacionales(test.getId());
        verify(testPreguntaService, times(1)).getPreguntasAutoPercepcion(test.getId());
        verify(estudianteRepository, times(1)).save(estudiante);
        assertEquals(1, estudiante.getIntentosTest());
    }

    @org.junit.jupiter.api.Test
    public void testStartTest_ThrowsResourceNotFoundException() {
        // Arrange
        TestRequestDTO requestDTO = new TestRequestDTO();
        requestDTO.setEstudiante_id(999L);

        when(estudianteRepository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> testService.startTest(requestDTO));
    }

    @org.junit.jupiter.api.Test
    public void testGetAllTests() {
        // Arrange
        List<com.its.orientaTest.model.entities.Test> tests = new ArrayList<>();
        tests.add(new com.its.orientaTest.model.entities.Test());
        tests.add(new com.its.orientaTest.model.entities.Test());

        when(testRepository.findAll()).thenReturn(tests);
        when(testMapper.toListDTO(tests)).thenReturn(new ArrayList<>());

        // Act
        List<TestResponseDTO> result = testService.getAllTests();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(testRepository, times(1)).findAll();
        verify(testMapper, times(1)).toListDTO(tests);
    }

    @org.junit.jupiter.api.Test
    public void testGetAllTests_NoTestsFound() {
        // Arrange
        when(testRepository.findAll()).thenReturn(new ArrayList<>());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> testService.getAllTests());
    }
  
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