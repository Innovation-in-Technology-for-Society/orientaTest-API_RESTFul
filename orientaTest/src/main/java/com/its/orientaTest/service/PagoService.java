package com.its.orientaTest.service;

import com.its.orientaTest.exceptions.ResourceNotFoundException;
import com.its.orientaTest.mapper.PagoMapper;
import com.its.orientaTest.model.dto.PagoBeneficioResponseDTO;
import com.its.orientaTest.model.entities.Pago;
import com.its.orientaTest.repository.EstudianteRepository;
import com.its.orientaTest.repository.PagoRepository;
import lombok.AllArgsConstructor;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class PagoService {
    
    private final PagoMapper pagoMapper;
    private final PagoRepository pagoRepository;
    private final EstudianteRepository estudianteRepository;

    public List<PagoBeneficioResponseDTO> getPagosBeneficioByEstudianteId(Long idEstudiante) {
        if (!estudianteRepository.existsById(idEstudiante)) {
            throw new ResourceNotFoundException("Estudiante no encontrado");
        }
        List<Pago> pagos = pagoRepository.findByIdEstudiante(idEstudiante);
        return pagoMapper.toListBeneficioDTO(pagos);
    }
}