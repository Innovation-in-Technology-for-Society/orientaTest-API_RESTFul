package com.its.orientaTest.mapper;

import com.its.orientaTest.model.dto.ResultadoRequestDTO;
import com.its.orientaTest.model.dto.ResultadoResponseDTO;
import com.its.orientaTest.model.entities.Resultado;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import java.util.List;

@Component
@AllArgsConstructor
public class ResultadoMapper {
    private final ModelMapper modelMapper;

    public Resultado toEntity(ResultadoRequestDTO resultadoRequestDTO){
        return modelMapper.map(resultadoRequestDTO, Resultado.class);
    }

    public ResultadoResponseDTO toDTO(Resultado resultado){
        return modelMapper.map(resultado, ResultadoResponseDTO.class);
    }

    public List<ResultadoResponseDTO> toListDTO(List<Resultado> resultados){
        return resultados.stream().map(this::toDTO).toList();
    }
}
