package com.its.orientaTest.service;

import com.its.orientaTest.exceptions.ResourceNotFoundException;
import com.its.orientaTest.mapper.TestMapper;
import com.its.orientaTest.model.dto.TestResponseDTO;
import com.its.orientaTest.model.entities.Test;
import com.its.orientaTest.repository.TestRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TestService {
    private final TestRepository testRepository;
    private final TestMapper testMapper;
    private final TestPreguntaService testPreguntaService;

    @Transactional
    public TestResponseDTO generateResultado(Long test_id){
        testPreguntaService.calculateResultado(test_id);

        Test test = testRepository.findById(test_id)
        .orElseThrow(() -> new ResourceNotFoundException("Test no encontrado con id " + test_id));

        return testMapper.toDTO(test);
    }
}
