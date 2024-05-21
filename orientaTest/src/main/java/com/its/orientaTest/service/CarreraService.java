package com.its.orientaTest.service;

import com.its.orientaTest.exceptions.ResourceDuplicateException;
import com.its.orientaTest.exceptions.ResourceNotFoundException;
import com.its.orientaTest.mapper.CarreraMapper;
import com.its.orientaTest.model.dto.CarreraRequestDTO;
import com.its.orientaTest.model.dto.CarreraResponseDTO;
import com.its.orientaTest.model.entities.Carrera;
import com.its.orientaTest.repository.CarreraRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;

import java.util.List;

@Service
@AllArgsConstructor
public class CarreraService {
    private final CarreraRepository carreraRepository;
    private final CarreraMapper carreraMapper;

    @Transactional
    public CarreraResponseDTO createCarrera(CarreraRequestDTO carreraRequestDTO){
        // Verificar si la Carrera existe
        if (carreraRepository.findByNombre(carreraRequestDTO.getNombre()).isPresent()){
            throw new ResourceDuplicateException("La carrera ya existe");
        }

        Carrera carrera = carreraMapper.toEntity(carreraRequestDTO);
        carreraRepository.save(carrera);
        return carreraMapper.toDTO(carrera);
    }

    @Transactional(readOnly = true)
    public List<CarreraResponseDTO> getAllCarreras(){
        List<Carrera> carreras = carreraRepository.findAll();
        return carreraMapper.toListDTO(carreras);
    }

    @Transactional(readOnly = true)
    public CarreraResponseDTO getCarreraByNombre(String nombre){
        Carrera carrera = carreraRepository.findByNombre(nombre)
        .orElseThrow(() -> new ResourceNotFoundException("Carrera no encontrada con nombre: " + nombre));
        return carreraMapper.toDTO(carrera);
    }

    @Transactional
    public CarreraResponseDTO updateCarrera(String nombre, CarreraRequestDTO carreraRequestDTO){
        Carrera carrera = carreraRepository.findByNombre(nombre)
        .orElseThrow(() -> new ResourceNotFoundException("Carrera no encontrada"));

        carrera.setDescripcion(carreraRequestDTO.getDescripcion());

        carrera = carreraRepository.save(carrera);
        return carreraMapper.toDTO(carrera);
    }

    @Transactional
    public void deleteCarrera(Long id){
        carreraRepository.deleteById(id);
    }
}
