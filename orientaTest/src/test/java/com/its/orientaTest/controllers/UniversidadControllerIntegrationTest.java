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
public class UniversidadControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetUniversidadByNombre() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/universidades/{nombre}", "Universidad Peruana Cayetano Heredia"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
