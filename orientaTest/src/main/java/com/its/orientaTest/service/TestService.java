package com.its.orientaTest.service;

import com.its.orientaTest.exceptions.ResourceNotFoundException;
import com.its.orientaTest.mapper.TestMapper;
import com.its.orientaTest.model.dto.TestRequestDTO;
import com.its.orientaTest.model.dto.TestResponseDTO;
import com.its.orientaTest.model.entities.Estudiante;
import com.its.orientaTest.model.entities.Test;
import com.its.orientaTest.repository.EstudianteRepository;
import com.its.orientaTest.repository.TestRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TestService {
    private final TestRepository testRepository;
    private final TestMapper testMapper;
    private final EstudianteRepository estudianteRepository;
    private final TestPreguntaService testPreguntaService;

    @Transactional
    public TestResponseDTO startTest(TestRequestDTO testRequestDTO){
        // Verificar que el Estudiante existe
        Estudiante estudiante = estudianteRepository.findById(testRequestDTO.getEstudiante_id())
        .orElseThrow(() -> new ResourceNotFoundException("El Estudiante con id " + testRequestDTO.getEstudiante_id() + "no existe"));

        Test test = testMapper.toEntity(testRequestDTO);
        test.setFechaTest(LocalDateTime.now());
        test.setEstudiante(estudiante);

        test = testRepository.save(test);

        estudiante.setIntentosTest(estudiante.getIntentosTest() + 1);
        estudianteRepository.save(estudiante);

        // Llama a los métodos con el test_id correcto
        testPreguntaService.getPreguntasVocacionales(test.getId());
        testPreguntaService.getPreguntasAutoPercepcion(test.getId());

        return testMapper.toDTO(test);
    }
    
    @Transactional(readOnly = true)
    public List<TestResponseDTO> getAllTests(){
        List<Test> tests = testRepository.findAll();
        return testMapper.toListDTO(tests);
    }

    @Transactional
    public TestResponseDTO generateResultado(Long test_id){
        testPreguntaService.calculateResultado(test_id);

        Test test = testRepository.findById(test_id)
        .orElseThrow(() -> new ResourceNotFoundException("Test no encontrado con id " + test_id));

        return testMapper.toDTO(test);
    }
}
