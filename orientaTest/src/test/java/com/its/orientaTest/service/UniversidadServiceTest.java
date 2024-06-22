package com.its.orientaTest.service;

import com.its.orientaTest.model.dto.UniversidadPrecisaResponseDTO;
import com.its.orientaTest.exceptions.ResourceNotFoundException;
import com.its.orientaTest.mapper.UniversidadMapper;
import com.its.orientaTest.model.dto.UniversidadResponseDTO;
import com.its.orientaTest.model.entities.Universidad;
import com.its.orientaTest.repository.UniversidadRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import java.util.Optional;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UniversidadServiceTest {
    @Mock
    private UniversidadRepository universidadRepository;

    @Mock
    private UniversidadMapper universidadMapper;

    @InjectMocks
    private UniversidadService universidadService;

    @Test
    void testGetAllUniversidades() {
        // Mock de datos de prueba
        Universidad universidad = new Universidad(1L, "Universidad XYZ", "xyz@example.com", "123456789", "Ubicación XYZ", 5, "Beneficio XYZ");
        UniversidadResponseDTO universidadResponseDTO = new UniversidadResponseDTO(1L, "Universidad XYZ", "xyz@example.com", "123456789", "Ubicación XYZ", 5);
        // Configuración del comportamiento de los mocks
        when(universidadRepository.findAll()).thenReturn(Collections.singletonList(universidad));
        when(universidadMapper.toListDTO(anyList())).thenReturn(Collections.singletonList(universidadResponseDTO));
        // Ejecución del método bajo prueba
        List<UniversidadResponseDTO> responseDTOList = universidadService.getAllUniversidades();
        // Verificación de resultados
        assertNotNull(responseDTOList);
        assertFalse(responseDTOList.isEmpty());
        assertEquals(1, responseDTOList.size());
        UniversidadResponseDTO responseDTO = responseDTOList.get(0);
        assertEquals("Universidad XYZ", responseDTO.getNombre());
        assertEquals("xyz@example.com", responseDTO.getCorreoElectronico());
        assertEquals("123456789", responseDTO.getTelefono());
        assertEquals("Ubicación XYZ", responseDTO.getUbicacion());
        assertEquals(5, responseDTO.getRanking());

        // Verificación de interacciones con los mocks
        verify(universidadRepository, times(1)).findAll();
    }

    @Test
    void testGetAllUniversidades_NoUniversidadesFound() {
        // Arrange
        when(universidadRepository.findAll()).thenReturn(Collections.emptyList());
        when(universidadMapper.toListDTO(anyList())).thenReturn(Collections.emptyList());
        // Act
        List<UniversidadResponseDTO> responseDTOList = universidadService.getAllUniversidades();
        // Assert
        assertNotNull(responseDTOList);
        assertTrue(responseDTOList.isEmpty());
        verify(universidadRepository, times(1)).findAll();
        verify(universidadMapper, times(1)).toListDTO(anyList());
    }

    @Test
    public void testGetUniversidadByNombre_ExistingName(){
        // Arrange
        String nombre = "Universidad Peruana de Ciencias Aplicadas";
        Universidad universidad = new Universidad();
        when (universidadRepository.findByNombre(nombre)).thenReturn(Optional.of(universidad));
        //Mocking mapper
        UniversidadResponseDTO responseDTO = new UniversidadResponseDTO();
        responseDTO.setNombre(nombre);
        when(universidadMapper.toDTO(universidad)).thenReturn(responseDTO);
        // Act
        UniversidadResponseDTO result = universidadService.getUniversidadByNombre(nombre);
        // Assert
        assertNotNull(result);
        assertEquals(nombre, result.getNombre());
    }

    @Test
    public void testGetUniversidadByNombre_NonExistingName(){
        // Arrange
        String nombre = "Universidad Tecnológica del Perú";
        when (universidadRepository.findByNombre(nombre)).thenReturn(Optional.empty()); 
        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> universidadService.getUniversidadByNombre(nombre));
    }
  
    @Test
    public void testGetUniversidadPrecisa_ReturnsEmptyList() {
        when(universidadRepository.findAll()).thenReturn(Collections.emptyList());
        when(universidadMapper.toListDTOPrecisa(Collections.emptyList())).thenReturn(Collections.emptyList());

        List<UniversidadPrecisaResponseDTO> result = universidadService.getUniversidadPrecisa();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetUniversidadPrecisa_ReturnsListOfDtos() {
        Universidad universidad1 = new Universidad();
        universidad1.setId(1L);
        universidad1.setNombre("Universidad 1");

        Universidad universidad2 = new Universidad();
        universidad2.setId(2L);
        universidad2.setNombre("Universidad 2");

        List<Universidad> universidades = Arrays.asList(universidad1, universidad2);

        when(universidadRepository.findAll()).thenReturn(universidades);

        UniversidadPrecisaResponseDTO dto1 = new UniversidadPrecisaResponseDTO();
        dto1.setId(1L);
        dto1.setNombre("Universidad 1");

        UniversidadPrecisaResponseDTO dto2 = new UniversidadPrecisaResponseDTO();
        dto2.setId(2L);
        dto2.setNombre("Universidad 2");

        when(universidadMapper.toListDTOPrecisa(universidades)).thenReturn(Arrays.asList(dto1, dto2));

        List<UniversidadPrecisaResponseDTO> result = universidadService.getUniversidadPrecisa();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(dto1, result.get(0));
        assertEquals(dto2, result.get(1));
    }

}
