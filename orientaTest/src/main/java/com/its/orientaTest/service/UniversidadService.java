package com.its.orientaTest.service;

import com.its.orientaTest.exceptions.ResourceNotFoundException;
import com.its.orientaTest.mapper.UniversidadMapper;
import com.its.orientaTest.model.entities.Universidad;
import com.its.orientaTest.model.dto.UniversidadResponseDTO;
import com.its.orientaTest.repository.UniversidadRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UniversidadService {
    private final UniversidadRepository universidadRepository;
    private final UniversidadMapper universidadMapper;

    @Transactional(readOnly = true)
    public UniversidadResponseDTO getUniversidadByNombre(String nombre){
        Universidad universidad = universidadRepository.findByNombre(nombre)
        .orElseThrow(() -> new ResourceNotFoundException("Universidad no encontrada con nombre: " + nombre));
        return universidadMapper.toDTO(universidad);
    }
}
