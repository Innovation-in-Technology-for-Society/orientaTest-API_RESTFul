package com.its.orientaTest.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.orientaTest.exceptions.ResourceNotFoundException;
import com.its.orientaTest.mapper.TestMapper;
import com.its.orientaTest.model.dto.TestResponseDTO;
import com.its.orientaTest.model.entities.Test;
import com.its.orientaTest.repository.TestRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TestService {
    private final TestRepository testRepository;
    private final TestMapper testMapper;
    
    @Transactional(readOnly = true)
    public TestResponseDTO getTestByEstudianteId(Long estudiante_id){
        Test test = testRepository.findByEstudianteId(estudiante_id)
        .orElseThrow(() -> new ResourceNotFoundException("El Estudiante con id " + estudiante_id + "no existe"));
        return testMapper.toDTO(test);
    }    
}
