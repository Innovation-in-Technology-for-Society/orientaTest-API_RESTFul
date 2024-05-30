package com.its.orientaTest.mapper;

import com.its.orientaTest.model.dto.PreguntaResponseDTO;
import com.its.orientaTest.model.dto.TestPreguntaRequestDTO;
import com.its.orientaTest.model.dto.TestPreguntaResponseDTO;
import com.its.orientaTest.model.entities.TestPregunta;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import java.util.List;

@Component
@AllArgsConstructor
public class TestPreguntaMapper {
    private final ModelMapper modelMapper;

    public TestPregunta toEntity(TestPreguntaRequestDTO testPreguntaRequestDTO){
        return modelMapper.map(testPreguntaRequestDTO, TestPregunta.class);
    }

    public TestPreguntaResponseDTO toDTO(TestPregunta testPregunta){
        return modelMapper.map(testPregunta, TestPreguntaResponseDTO.class);
    }

    public List<TestPreguntaResponseDTO> toListDTO(List<TestPregunta> testPreguntas){
        return testPreguntas.stream().map(this::toDTO).toList();
    }
}
