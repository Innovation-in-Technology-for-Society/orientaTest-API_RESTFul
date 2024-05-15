package com.its.orientaTest.mapper;

import com.its.orientaTest.model.dto.CarreraRequestDTO;
import com.its.orientaTest.model.dto.CarreraResponseDTO;
import com.its.orientaTest.model.entities.Carrera;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import java.util.List;

@Component
@AllArgsConstructor
public class CarreraMapper {
    private final ModelMapper modelMapper;

    public Carrera toEntity(CarreraRequestDTO carreraRequestDTO){
        return modelMapper.map(carreraRequestDTO, Carrera.class);
    }

    public CarreraResponseDTO toDTO(Carrera carrera){
        return modelMapper.map(carrera, CarreraResponseDTO.class);
    }

    public List<CarreraResponseDTO> toListDTO(List<Carrera> carreras){
        return carreras.stream().map(this::toDTO).toList();
    }
}
