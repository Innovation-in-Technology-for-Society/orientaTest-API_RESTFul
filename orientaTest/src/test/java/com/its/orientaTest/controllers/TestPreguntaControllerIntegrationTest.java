package com.its.orientaTest.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class TestPreguntaControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetResultadosTipoTestAutopercepcion() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/test-preguntas/{test_id}/{tipoTest}", 1L, "auto-percepcion")).andExpect(MockMvcResultMatchers.status().isOk());
    }
  
    @Test
    public void testGetResultadosTipoTestVocacional() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/test-preguntas/{test_id}/{tipoTest}", 1L, "vocacional")).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
