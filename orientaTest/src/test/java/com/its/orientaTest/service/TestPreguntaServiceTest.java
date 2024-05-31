package com.its.orientaTest.service;

import com.its.orientaTest.repository.ResultadoRepository;
import com.its.orientaTest.repository.CarreraUniversidadRepository;
import com.its.orientaTest.repository.CarreraRepository;
import com.its.orientaTest.model.entities.Universidad;
import com.its.orientaTest.model.entities.Resultado;
import com.its.orientaTest.model.entities.CarreraUniversidad;
import com.its.orientaTest.model.entities.Carrera;
import static org.mockito.ArgumentMatchers.any;
import com.its.orientaTest.mapper.TestPreguntaMapper;
import com.its.orientaTest.model.dto.PreguntaResponseDTO;
import com.its.orientaTest.model.entities.Pregunta;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import com.its.orientaTest.repository.UniversidadRepository;
import java.util.Collections;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import com.its.orientaTest.exceptions.ResourceNotFoundException;
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

import org.springframework.dao.DataAccessException;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TestPreguntaServiceTest {

    @Mock
    private TestPreguntaMapper testPreguntaMapper;
  
    @Mock
    private TestRepository testRepository;
  
    @Mock
    private ResultadoRepository resultadoRepository;
  
    @Mock
    private UniversidadRepository universidadRepository;
  
    @Mock
    private CarreraUniversidadRepository carreraUniversidadRepository;
  
    @Mock
    private CarreraRepository carreraRepository;

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
  
    @Test
    public void testGetPreguntasVocacionales_Success() {
        // Arrange
        Long test_id = 1L;
        List<Pregunta> preguntas = new ArrayList<>();
        preguntas.add(new Pregunta());
        preguntas.add(new Pregunta());

        when(preguntaRepository.findVocacional()).thenReturn(preguntas);
        when(testRepository.findById(test_id)).thenReturn(java.util.Optional.of(new com.its.orientaTest.model.entities.Test()));

        // Act & Assert
        assertDoesNotThrow(() -> testPreguntaService.getPreguntasVocacionales(test_id));
    }

    @Test
    public void testGetPreguntasVocacionales_NotFound() {
        // Arrange
        Long test_id = 1L;
        when(preguntaRepository.findVocacional()).thenReturn(new ArrayList<>());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> testPreguntaService.getPreguntasVocacionales(test_id));
    }

    @Test
    public void testGetPreguntasAutoPercepcion_Success() {
        // Arrange
        Long test_id = 1L;
        List<Pregunta> preguntas = new ArrayList<>();
        preguntas.add(new Pregunta());
        preguntas.add(new Pregunta());

        when(preguntaRepository.findAutoPercepcion()).thenReturn(preguntas);
        when(testRepository.findById(test_id)).thenReturn(java.util.Optional.of(new com.its.orientaTest.model.entities.Test()));

        // Act & Assert
        assertDoesNotThrow(() -> testPreguntaService.getPreguntasAutoPercepcion(test_id));
    }

    @Test
    public void testGetPreguntasAutoPercepcion_NotFound() {
        // Arrange
        Long test_id = 1L;
        when(preguntaRepository.findAutoPercepcion()).thenReturn(new ArrayList<>());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> testPreguntaService.getPreguntasAutoPercepcion(test_id));
    }

    @Test
    public void testSavePreguntas_Success() {
        // Arrange
        Long test_id = 1L;
        List<Pregunta> preguntas = new ArrayList<>();
        preguntas.add(new Pregunta());
        preguntas.add(new Pregunta());

        com.its.orientaTest.model.entities.Test test = new com.its.orientaTest.model.entities.Test();
        test.setId(test_id);

        when(testRepository.findById(test_id)).thenReturn(java.util.Optional.of(test));
        when(testPreguntaRepository.save(any(TestPregunta.class))).thenReturn(new TestPregunta());

        // Act & Assert
        assertDoesNotThrow(() -> testPreguntaService.savePreguntas(test_id, preguntas, "test_type"));
    }

    @Test
    public void testSavePreguntas_Exception() {
        // Arrange
        Long test_id = 1L;
        List<Pregunta> preguntas = new ArrayList<>();
        preguntas.add(new Pregunta());
        preguntas.add(new Pregunta());

        com.its.orientaTest.model.entities.Test test = new com.its.orientaTest.model.entities.Test();
        test.setId(test_id);

        when(testRepository.findById(test_id)).thenReturn(java.util.Optional.of(test));
        when(testPreguntaRepository.save(any(TestPregunta.class))).thenThrow(DataAccessException.class);

        // Act & Assert
        assertThrows(DataAccessException.class, () -> testPreguntaService.savePreguntas(test_id, preguntas, "test_type"));
    }

    @Test
    public void testAnswerPregunta_Success() {
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

    @Test
    public void testAnswerPregunta_TestNotFound() {
        // Arrange
        Long test_id = 1L;
        Long pregunta_id = 1L;
        TestPreguntaRequestDTO requestDTO = new TestPreguntaRequestDTO();
        requestDTO.setValor(5);

        when(testRepository.findById(test_id)).thenReturn(java.util.Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> testPreguntaService.answerPregunta(test_id, pregunta_id, requestDTO));
    }

    @Test
    public void testAnswerPregunta_PreguntaNotFound() {
        // Arrange
        Long test_id = 1L;
        Long pregunta_id = 1L;
        TestPreguntaRequestDTO requestDTO = new TestPreguntaRequestDTO();
        requestDTO.setValor(5);

        com.its.orientaTest.model.entities.Test test = new com.its.orientaTest.model.entities.Test();
        test.setId(test_id);

        when(testRepository.findById(test_id)).thenReturn(java.util.Optional.of(test));
        when(testPreguntaRepository.findById(pregunta_id)).thenReturn(java.util.Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> testPreguntaService.answerPregunta(test_id, pregunta_id, requestDTO));
    }
  
    @Test
    public void testGetResultadosTipoTest_ReturnsEmptyList() {
        Long test_id = 1L;
        String tipoTest = "auto-percepcion";
    @Test
    public void testGetResultadosTipoTestVocacional_ReturnsEmptyList() {
        Long test_id = 1L;
        String tipoTest = "vocacional";
        when(testPreguntaRepository.findByTestIdAndTipoTest(test_id, tipoTest)).thenReturn(Collections.emptyList());
        List<TestPreguntaResponseDTO> result = testPreguntaService.getResultadosTipoTest(test_id, tipoTest);
        assertNotNull(result);
        assertTrue(result.isEmpty());
      
    @Test
    public void testGetResultadosTipoTestAutopercepcion_ReturnsEmptyList() {
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

      public void testGetResultadosTipoTestVocacional_ReturnsListOfDtos() {
        Long test_id = 1L;
        String tipoTest = "vocacional";
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

      public void testGetResultadosTipoTestAutopercepcion_ReturnsListOfDtos() {
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
