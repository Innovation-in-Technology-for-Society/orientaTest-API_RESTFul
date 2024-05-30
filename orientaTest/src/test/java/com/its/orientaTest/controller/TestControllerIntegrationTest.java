package com.its.orientaTest.controller;

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
public class TestControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGenerateResultado() throws Exception {
        // Simular una solicitud de creación de resultado de prueba con un ID de prueba
        mockMvc.perform(MockMvcRequestBuilders.post("/tests/1/resultado")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
