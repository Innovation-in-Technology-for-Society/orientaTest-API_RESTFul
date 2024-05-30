package com.its.orientaTest.service;

import com.its.orientaTest.exceptions.ResourceNotFoundException;
import com.its.orientaTest.mapper.ResultadoMapper;
import com.its.orientaTest.model.dto.CarreraUniversidadResponseDTO;
import com.its.orientaTest.model.dto.ResultadoResponseDTO;
import com.its.orientaTest.model.dto.TestResponseDTO;
import com.its.orientaTest.model.entities.CarreraUniversidad;
import com.its.orientaTest.model.entities.Resultado;
import com.its.orientaTest.repository.ResultadoRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ResultadoServiceTest {
    
    @Mock
    private ResultadoRepository resultadoRepository;
    @Mock
    private ResultadoMapper resultadoMapper;

    @InjectMocks
    private ResultadoService resultadoService;

    @Test
    public void testGetResultadoByTestId_ExistingId(){
        // Arrange
        Long testId = 1L;
        Long resultadoId = 1L;

        com.its.orientaTest.model.entities.Test test = new com.its.orientaTest.model.entities.Test();
        test.setId(testId);

        CarreraUniversidad carreraUniversidad = new CarreraUniversidad();
        carreraUniversidad.setId(1L);  // Suponiendo que hay un campo id en CarreraUniversidad

        Resultado resultado = new Resultado();
        resultado.setId(resultadoId);
        resultado.setTest(test);
        resultado.setCarreraUniversidad(carreraUniversidad);

        when(resultadoRepository.findByTestId(testId)).thenReturn(Optional.of(resultado));

        // Mocking mapper
        TestResponseDTO testResponseDTO = new TestResponseDTO();
        testResponseDTO.setId(testId);

        CarreraUniversidadResponseDTO carreraUniversidadResponseDTO = new CarreraUniversidadResponseDTO();
        carreraUniversidadResponseDTO.setId(1L);

        ResultadoResponseDTO responseDTO = new ResultadoResponseDTO();
        responseDTO.setId(resultadoId);
        responseDTO.setTest_id(testResponseDTO);
        responseDTO.setCarreraUniversidad(carreraUniversidadResponseDTO);

        when(resultadoMapper.toDTO(resultado)).thenReturn(responseDTO);

        // Act
        ResultadoResponseDTO result = resultadoService.getResultadoByTestId(testId);

        // Assert
        assertNotNull(result);
        assertEquals(resultadoId, result.getId());

        assertNotNull(result.getTest_id());
        assertEquals(testId, result.getTest_id().getId());

        assertNotNull(result.getCarreraUniversidad());
        assertEquals(1L, result.getCarreraUniversidad().getId());
    }

    @Test 
    public void testGetResultadoByTestId_NonExistingId(){
        // Arrange
        Long test_id = 2L;
        when(resultadoRepository.findByTestId(test_id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> resultadoService.getResultadoByTestId(test_id));
    }
}