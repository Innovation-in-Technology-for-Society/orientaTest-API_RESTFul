package com.its.orientaTest.service;

import com.its.orientaTest.exceptions.ResourceDuplicateException;
import com.its.orientaTest.exceptions.ResourceNotFoundException;
import com.its.orientaTest.mapper.UniversidadMapper;
import com.its.orientaTest.model.entities.Universidad;
import com.its.orientaTest.model.dto.UniversidadRequestDTO;
import com.its.orientaTest.model.dto.UniversidadResponseDTO;
import com.its.orientaTest.repository.UniversidadRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;

import java.util.List;

@Service
@AllArgsConstructor
public class UniversidadService {
    private final UniversidadRepository universidadRepository;
    private final UniversidadMapper universidadMapper;

    
    @Transactional
    public UniversidadResponseDTO createUniversidad(UniversidadRequestDTO universidadRequestDTO){
        // Verificar si la Universidad existe
        if (universidadRepository.findByNombre(universidadRequestDTO.getNombre()).isPresent()){
            throw new ResourceDuplicateException("La universidad ya existe");
        }

        Universidad universidad = universidadMapper.toEntity(universidadRequestDTO);
        universidadRepository.save(universidad);
        return universidadMapper.toDTO(universidad);
    }

    @Transactional(readOnly = true)
    public List<UniversidadResponseDTO> getAllUniversidades(){
        List<Universidad> universidades = universidadRepository.findAll();
        return universidadMapper.toListDTO(universidades);
    }
}
