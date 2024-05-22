package com.its.orientaTest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.its.orientaTest.model.dto.PreguntaResponseDTO;
import com.its.orientaTest.model.dto.TestPreguntaResponseDTO;
import com.its.orientaTest.repository.TestPreguntaRepository;
import com.its.orientaTest.model.entities.Pregunta;
import com.its.orientaTest.model.entities.TestPregunta;

import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TestPreguntaService {
    private final TestPreguntaRepository testPreguntaRepository;

    @Transactional(readOnly = true)
    public List<TestPreguntaResponseDTO> getResultadosAutoPercepcion(Long testId, String tipoTest) {
        List<TestPregunta> preguntas = testPreguntaRepository.findByTestIdAndTipoTest(testId, tipoTest);
        return preguntas.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    private TestPreguntaResponseDTO mapToResponseDTO(TestPregunta testPregunta) {
        TestPreguntaResponseDTO dto = testPreguntaMapper.toDTO(testPregunta);
        Pregunta pregunta = testPregunta.getPregunta();
        PreguntaResponseDTO preguntaDTO = new PreguntaResponseDTO();
        preguntaDTO.setId(pregunta.getId());
        preguntaDTO.setEnunciado(pregunta.getEnunciado());
        dto.setPregunta_id(preguntaDTO);
        return dto;
    }
}
