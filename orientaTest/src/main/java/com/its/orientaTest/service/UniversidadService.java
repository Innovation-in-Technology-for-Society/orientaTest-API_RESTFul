package com.its.orientaTest.service;

import com.its.orientaTest.mapper.UniversidadMapper;
import com.its.orientaTest.model.entities.Universidad;
import com.its.orientaTest.model.dto.UniversidadPrecisaResponseDTO;
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

    @Transactional(readOnly = true)
    public List<UniversidadPrecisaResponseDTO> getUniversidadPrecisa(){
        List<Universidad> universidad = universidadRepository.findAll();
        return universidadMapper.toListDTOPrecisa((universidad));
    }
}
