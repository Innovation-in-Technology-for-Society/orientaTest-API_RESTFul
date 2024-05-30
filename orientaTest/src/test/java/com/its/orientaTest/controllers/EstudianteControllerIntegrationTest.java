package com.its.orientaTest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.its.orientaTest.model.dto.EstudianteRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class EstudianteControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateEstudiante() throws Exception {
        // Estudiante
        EstudianteRequestDTO estudiante = new EstudianteRequestDTO();
        estudiante.setNombre("Juan");
        estudiante.setApellido("Pérez");
        estudiante.setCorreoElectronico("juan.perez@example.com");
        estudiante.setContrasenia("password");
        estudiante.setTelefono("123456789");
        estudiante.setDireccion("Dirección 123");

        // Convertir a JSON
        String estudianteJson = objectMapper.writeValueAsString(estudiante);

        // Realizar la llamada HTTP
        mockMvc.perform(MockMvcRequestBuilders.post("/estudiantes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(estudianteJson))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
