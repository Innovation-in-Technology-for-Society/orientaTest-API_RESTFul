package com.its.orientaTest.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import com.its.orientaTest.exceptions.ResourceNotFoundException;
import com.its.orientaTest.model.entities.Carrera;
import com.its.orientaTest.model.entities.CarreraUniversidad;
import com.its.orientaTest.model.entities.Resultado;
import com.its.orientaTest.model.entities.TestPregunta;
import com.its.orientaTest.model.entities.Universidad;
import com.its.orientaTest.repository.CarreraRepository;
import com.its.orientaTest.repository.CarreraUniversidadRepository;
import com.its.orientaTest.repository.ResultadoRepository;
import com.its.orientaTest.repository.TestPreguntaRepository;
import com.its.orientaTest.repository.TestRepository;
import com.its.orientaTest.repository.UniversidadRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestPreguntaServiceTest {

    @Mock
    private TestRepository testRepository;

    @Mock
    private TestPreguntaRepository testPreguntaRepository;

    @Mock
    private CarreraUniversidadRepository carreraUniversidadRepository;

    @Mock
    private CarreraRepository carreraRepository;

    @Mock
    private UniversidadRepository universidadRepository;

    @Mock
    private ResultadoRepository resultadoRepository;

    @InjectMocks
    private TestPreguntaService testPreguntaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @org.junit.jupiter.api.Test
    public void testCalculateResultado() {
        // Arrange
        Long testId = 1L;
        com.its.orientaTest.model.entities.Test test = new com.its.orientaTest.model.entities.Test();
        test.setId(testId);

        List<TestPregunta> testPreguntas = new ArrayList<>();
        TestPregunta testPregunta1 = new TestPregunta();
        TestPregunta testPregunta2 = new TestPregunta();
        testPreguntas.add(testPregunta1);
        testPreguntas.add(testPregunta2);

        when(testRepository.findById(testId)).thenReturn(java.util.Optional.of(test));
        when(testPreguntaRepository.findByTestId(testId)).thenReturn(testPreguntas);

        Universidad universidad = new Universidad();
        universidad.setId(1L);
        universidad.setNombre("Universidad Tecnológica del Perú");
        universidad.setCorreoElectronico("example@universidad.com");
        universidad.setTelefono("123456789");
        universidad.setUbicacion("Lima");
        universidad.setRanking(1);
        universidad.setBeneficio("Beca de estudios");
        when(universidadRepository.findByNombre("Universidad Tecnológica del Perú")).thenReturn(java.util.Optional.of(universidad));

        Carrera carrera = new Carrera();
        carrera.setId(1L);
        carrera.setNombre("Ingeniería de Software");
        when(carreraRepository.findByNombre("Ingeniería de Software")).thenReturn(java.util.Optional.of(carrera));

        CarreraUniversidad carreraUniversidad = new CarreraUniversidad();
        carreraUniversidad.setId(1L);
        when(carreraUniversidadRepository.findByUniversidadAndCarrera(1L, 1L)).thenReturn(java.util.Optional.of(carreraUniversidad));

        // Act
        assertDoesNotThrow(() -> testPreguntaService.calculateResultado(testId));

        // Assert
        verify(testRepository, times(1)).findById(testId);
        verify(testPreguntaRepository, times(1)).findByTestId(testId);
        verify(universidadRepository, times(1)).findByNombre("Universidad Tecnológica del Perú");
        verify(carreraRepository, times(1)).findByNombre("Ingeniería de Software");
        verify(carreraUniversidadRepository, times(1)).findByUniversidadAndCarrera(1L, 1L);
        verify(resultadoRepository, times(1)).save(any(Resultado.class));
    }

    @Test
public void testCalculateResultado_ThrowsResourceNotFoundException() {
    // Arrange
    Long testId = 1L;

    when(testRepository.findById(testId)).thenReturn(java.util.Optional.empty());

    // Act & Assert
    assertThrows(ResourceNotFoundException.class, () -> testPreguntaService.calculateResultado(testId));

    // Verify
    verify(testRepository, times(1)).findById(testId);
    verifyNoMoreInteractions(testPreguntaRepository, universidadRepository, carreraRepository, carreraUniversidadRepository, resultadoRepository);
}

}
