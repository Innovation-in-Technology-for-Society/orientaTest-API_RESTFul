package com.its.orientaTest.mapper;

import com.its.orientaTest.model.dto.UniversidadRequestDTO;
import com.its.orientaTest.model.dto.UniversidadResponseDTO;
import com.its.orientaTest.model.dto.UniversidadPrecisaResponseDTO;
import com.its.orientaTest.model.entities.Universidad;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import java.util.List;

@Component
@AllArgsConstructor
public class UniversidadMapper {
    private final ModelMapper modelMapper;

    public Universidad toEntity(UniversidadRequestDTO universidadRequestDTO){
        return modelMapper.map(universidadRequestDTO, Universidad.class);
    }

    public UniversidadResponseDTO toDTO(Universidad universidad){
        return modelMapper.map(universidad, UniversidadResponseDTO.class);
    }

    public List<UniversidadResponseDTO> toListDTO(List<Universidad> universidades){
        return universidades.stream().map(this::toDTO).toList();
    }

    public UniversidadPrecisaResponseDTO toDTOPrecisa(Universidad universidad){
        return modelMapper.map(universidad, UniversidadPrecisaResponseDTO.class);
    }

    
}
