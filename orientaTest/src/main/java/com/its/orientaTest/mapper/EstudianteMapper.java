package com.its.orientaTest.mapper;

import com.its.orientaTest.model.dto.EstudianteRequestDTO;
import com.its.orientaTest.model.dto.EstudianteResponseDTO;
import com.its.orientaTest.model.entities.Estudiante;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import java.util.List;

@Component
@AllArgsConstructor
public class EstudianteMapper {
    private final ModelMapper modelMapper;

    public Estudiante toEntity(EstudianteRequestDTO estudianteRequestDTO){
        return modelMapper.map(estudianteRequestDTO, Estudiante.class);
    }

    public EstudianteResponseDTO toDTO(Estudiante estudiante){
        return modelMapper.map(estudiante, EstudianteResponseDTO.class);
    }

    public List<EstudianteResponseDTO> toListDTO(List<Estudiante> estudiantes){
        return estudiantes.stream().map(this::toDTO).toList();
    }

    public void updateEntity(Estudiante estudiante, EstudianteRequestDTO estudianteRequestDTO) {
        estudiante.setNombre(estudianteRequestDTO.getNombre());
        estudiante.setApellido(estudianteRequestDTO.getApellido());
        estudiante.setCorreoElectronico(estudianteRequestDTO.getCorreoElectronico());
        estudiante.setContrasenia(estudianteRequestDTO.getContrasenia());
    }
}
