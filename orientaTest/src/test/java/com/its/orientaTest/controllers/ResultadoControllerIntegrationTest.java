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
public class ResultadoControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetResultadoByTestId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/resultados/{test_id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
