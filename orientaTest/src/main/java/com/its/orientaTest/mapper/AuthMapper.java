package com.its.orientaTest.mapper;

import com.its.orientaTest.model.dto.AuthRequestDTO;
import com.its.orientaTest.model.dto.AuthResponseDTO;
import com.its.orientaTest.model.dto.EstudianteResponseDTO;
import com.its.orientaTest.model.entities.Estudiante;

import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
 @AllArgsConstructor
public class AuthMapper {

    private final ModelMapper modelMapper;

    public Estudiante toEntity(AuthRequestDTO authRequestDTO) {
        return modelMapper.map(authRequestDTO, Estudiante.class);
    }

    public AuthResponseDTO tDto(Estudiante estudiante){
        return modelMapper.map(estudiante, AuthResponseDTO.class);
    }
    
    public AuthResponseDTO toAuthResponseDTO(String token, EstudianteResponseDTO estudianteResponseDTO) {
        return new AuthResponseDTO(token, estudianteResponseDTO);
    }

    
}
