package com.its.orientaTest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.its.orientaTest.mapper.TestPreguntaMapper;
import com.its.orientaTest.model.dto.TestPreguntaResponseDTO;
import com.its.orientaTest.repository.TestPreguntaRepository;
import com.its.orientaTest.model.entities.TestPregunta;

import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TestPreguntaService {
    private final TestPreguntaMapper testPreguntaMapper;
    private final TestPreguntaRepository testPreguntaRepository;

    @Transactional(readOnly = true)
    public List<TestPreguntaResponseDTO> getResultadosTipoTest(Long testId, String tipoTest) {
        List<TestPregunta> preguntas = testPreguntaRepository.findByTestIdAndTipoTest(testId, tipoTest);
        return preguntas.stream()
                .map(testPreguntaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}