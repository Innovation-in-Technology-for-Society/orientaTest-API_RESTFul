package com.its.orientaTest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.its.orientaTest.model.dto.CarreraRequestDTO;
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
public class CarreraControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateCarrera() throws Exception {
        // Carrera 1
        CarreraRequestDTO carrera1 = new CarreraRequestDTO();
        carrera1.setNombre("Ingeniería Mecatrónica");
        carrera1.setDescripcion("Combina mecánica, electrónica, control y sistemas computacionales para diseñar y desarrollar productos y procesos automatizados. Se enfoca en la integración de tecnología avanzada para mejorar la eficiencia y funcionalidad.");
        carrera1.setInversion("S/.86,175");

        // Carrera 2
        CarreraRequestDTO carrera2 = new CarreraRequestDTO();
        carrera2.setNombre("Medicina");
        carrera2.setDescripcion("La Medicina es la ciencia y práctica dedicada al diagnóstico, tratamiento y prevención de enfermedades y lesiones en seres humanos. Involucra conocimientos de anatomía, fisiología, farmacología y ética para mejorar la salud y el bienestar de las personas.");
        carrera2.setInversion("S/.235,200 - S/.313,600");

        // Carrera 3
        CarreraRequestDTO carrera3 = new CarreraRequestDTO();
        carrera3.setNombre("Ingeniería Biomédica");
        carrera3.setDescripcion("Aplica principios de ingeniería y ciencias biológicas para desarrollar tecnologías y dispositivos médicos. Su objetivo es mejorar el diagnóstico, tratamiento y monitoreo de enfermedades, así como diseñar prótesis y equipos para la salud.");
        carrera3.setInversion("S/.77,760 - S/.281,020");

        // Convertir a JSON
        String carrera1Json = objectMapper.writeValueAsString(carrera1);
        String carrera2Json = objectMapper.writeValueAsString(carrera2);
        String carrera3Json = objectMapper.writeValueAsString(carrera3);

        // Realizar las llamadas HTTP
        mockMvc.perform(MockMvcRequestBuilders.post("/carreras")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(carrera1Json))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.post("/carreras")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(carrera2Json))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.post("/carreras")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(carrera3Json))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
