package com.its.orientaTest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.its.orientaTest.model.dto.TestRequestDTO;
import com.its.orientaTest.model.dto.TestResponseDTO;
import com.its.orientaTest.service.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TestControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TestService testService;

    @Test
    public void testStartTest() throws Exception {
        TestRequestDTO requestDTO = new TestRequestDTO();
        requestDTO.setEstudiante_id(1L);

        TestResponseDTO responseDTO = new TestResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setTipo_test("vocacional");
        responseDTO.setFecha_test(LocalDateTime.of(2024, 5, 29, 12, 0, 0));
        responseDTO.setEstudiante_id(1L);

        when(testService.startTest(any(TestRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/tests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.tipo_test").value("vocacional"))
                .andExpect(jsonPath("$.fecha_test").value("2024-05-29T12:00:00"))
                .andExpect(jsonPath("$.estudiante_id").value(1L));
    }

    @Test
    public void testGetAllTests() throws Exception {
        TestResponseDTO responseDTO = new TestResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setTipo_test("vocacional");
        responseDTO.setFecha_test(LocalDateTime.of(2024, 5, 29, 12, 0, 0));
        responseDTO.setEstudiante_id(1L);

        List<TestResponseDTO> responseDTOList = Collections.singletonList(responseDTO);

        when(testService.getAllTests()).thenReturn(responseDTOList);

        mockMvc.perform(get("/tests")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].tipo_test").value("vocacional"))
                .andExpect(jsonPath("$[0].fecha_test").value("2024-05-29T12:00:00"))
                .andExpect(jsonPath("$[0].estudiante_id").value(1L));
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
    }
    
    @Test
    public void testGetTestByEstudianteIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/tests/{estudiante_id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
