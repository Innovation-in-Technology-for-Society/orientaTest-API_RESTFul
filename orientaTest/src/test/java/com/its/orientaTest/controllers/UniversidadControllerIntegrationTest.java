package com.its.orientaTest.controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.its.orientaTest.model.dto.UniversidadRequestDTO;
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
public class UniversidadControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateUniversidad() throws Exception {
        // Universidad 1
        UniversidadRequestDTO universidad1 = new UniversidadRequestDTO();
        universidad1.setNombre("Universidad Peruana de Ciencias Aplicadas");
        universidad1.setCorreoElectronico("contacto@upc.edu.pe");
        universidad1.setTelefono("8120084");
        universidad1.setUbicacion("Surco");
        universidad1.setRanking(5);
        universidad1.setBeneficio("Becas y beneficios socioeconómicos a todos los alumnos que cumplen con los requisitos; Los estudiantes tienen un 90% de probabilidades de conseguir trabajo rápidamente y un 94% de ejercer su profesión");

        // Universidad 2
        UniversidadRequestDTO universidad2 = new UniversidadRequestDTO();
        universidad2.setNombre("Universidad Tecnológica del Perú");
        universidad2.setCorreoElectronico("contacto@utp.edu.pe");
        universidad2.setTelefono("2524445");
        universidad2.setUbicacion("Villa El Salvador");
        universidad2.setRanking(7);
        universidad2.setBeneficio("Programas para asegurar tu empleabilidad y tiene una infraestructura y tecnología muy moderna; Más de 500 convenios internacionales y becas de intercambio con las mejores universidades del mundo");

        // Universidad 3
        UniversidadRequestDTO universidad3 = new UniversidadRequestDTO();
        universidad3.setNombre("Universidad Peruana Cayetano Heredia");
        universidad3.setCorreoElectronico("contacto@upch.edu.pe");
        universidad3.setTelefono("9866473");
        universidad3.setUbicacion("SMP");
        universidad3.setRanking(10);
        universidad3.setBeneficio("Ofrece apoyo al talento académico/deportivo, así como a jóvenes con carencias económicas, a través de la Unidad de Becas y Créditos Educativos; Alta empleabilidad a nivel nacional e internacional");

        // Convertir a JSON
        String universidad1Json = objectMapper.writeValueAsString(universidad1);
        String universidad2Json = objectMapper.writeValueAsString(universidad2);
        String universidad3Json = objectMapper.writeValueAsString(universidad3);

        // Realizar las llamadas HTTP
        mockMvc.perform(MockMvcRequestBuilders.post("/universidades")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(universidad1Json))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.post("/universidades")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(universidad2Json))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.post("/universidades")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(universidad3Json))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
  
    @Test
    public void TestGetEconomicBenefitUniversidad() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/universidades/beneficio-socioeconomico")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }   
    @Test
    public void testGetUniversidadByNombre() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/universidades/{nombre}", "Universidad Peruana Cayetano Heredia"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
