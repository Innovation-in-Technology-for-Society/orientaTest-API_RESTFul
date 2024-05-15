package com.its.orientaTest.mapper;

import com.its.orientaTest.model.dto.PreguntaRequestDTO;
import com.its.orientaTest.model.dto.PreguntaResponseDTO;
import com.its.orientaTest.model.entities.Pregunta;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import java.util.List;

@Component
@AllArgsConstructor
public class PreguntaMapper {
    private final ModelMapper modelMapper;

    public Pregunta toEntity(PreguntaRequestDTO preguntaRequestDTO){
        return modelMapper.map(preguntaRequestDTO, Pregunta.class);
    }

    public PreguntaResponseDTO toDTO(Pregunta pregunta){
        return modelMapper.map(pregunta, PreguntaResponseDTO.class);
    }

    public List<PreguntaResponseDTO> toListDTO(List<Pregunta> preguntas){
        return preguntas.stream().map(this::toDTO).toList();
    }
}
