package com.its.orientaTest.service;

import com.its.orientaTest.exceptions.ResourceNotFoundException;
import com.its.orientaTest.mapper.TestPreguntaMapper;
import com.its.orientaTest.model.dto.TestPreguntaRequestDTO;
import com.its.orientaTest.model.dto.TestPreguntaResponseDTO;
import com.its.orientaTest.model.entities.Pregunta;
import com.its.orientaTest.model.entities.Test;
import com.its.orientaTest.model.entities.TestPregunta;
import com.its.orientaTest.repository.PreguntaRepository;
import com.its.orientaTest.repository.TestPreguntaRepository;
import com.its.orientaTest.repository.TestRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class TestPreguntaService {
    private final TestPreguntaMapper testPreguntaMapper;
    private final TestRepository testRepository;
    private final PreguntaRepository preguntaRepository;
    private final TestPreguntaRepository testPreguntaRepository;

    @Transactional 
    public void getPreguntasVocacionales(Long test_id){
        List<Pregunta> preguntas = preguntaRepository.findVocacional();
        Collections.shuffle(preguntas);
        preguntas = preguntas.subList(0, Math.min(preguntas.size(), 25));
        savePreguntas(test_id,  preguntas, "vocacional");
    }

    @Transactional 
    public void getPreguntasAutoPercepcion(Long test_id){
        List<Pregunta> preguntas = preguntaRepository.findAutoPercepcion();
        Collections.shuffle(preguntas);
        preguntas = preguntas.subList(0, Math.min(preguntas.size(), 10));
        savePreguntas(test_id,  preguntas, "auto-percepcion");
    }

    @Transactional
    public void savePreguntas(Long test_id, List<Pregunta> preguntas, String tipo_test){
        for (Pregunta pregunta : preguntas){

            // Verificar que el test se realizó 
            Test test = testRepository.findById(test_id)
            .orElseThrow(() -> new ResourceNotFoundException("Test no encontrado con id: " + test_id));

            TestPregunta testPregunta = new TestPregunta();
            testPregunta.setTest(test);
            testPregunta.setPregunta(pregunta);
            testPregunta.setTipoTest(tipo_test);
            testPregunta.setValor(0);
            testPregunta = testPreguntaRepository.save(testPregunta);
        }
    }
}
