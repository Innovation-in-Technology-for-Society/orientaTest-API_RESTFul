package com.its.orientaTest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.its.orientaTest.model.dto.TestPreguntaRequestDTO;
import com.its.orientaTest.model.dto.TestPreguntaResponseDTO;
import com.its.orientaTest.service.TestPreguntaService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestPreguntaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TestPreguntaService testPreguntaService;

    @Test
    public void testAnswerPregunta() throws Exception {
        TestPreguntaRequestDTO requestDTO = new TestPreguntaRequestDTO();
        requestDTO.setValor(5);

        TestPreguntaResponseDTO responseDTO = new TestPreguntaResponseDTO();
        responseDTO.setTest_id(1L);
        responseDTO.setPregunta_id(1L);
        responseDTO.setValor(5);

        when(testPreguntaService.answerPregunta(anyLong(), anyLong(), any(TestPreguntaRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/test-preguntas/{test_id}/{id}/responder", 1L, 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.test_id").value(1L))
                .andExpect(jsonPath("$.pregunta_id").value(1L))
                .andExpect(jsonPath("$.valor").value(5));
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
