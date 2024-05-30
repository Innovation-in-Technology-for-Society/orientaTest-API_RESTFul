package com.its.orientaTest.service;

import com.its.orientaTest.exceptions.ResourceNotFoundException;
import com.its.orientaTest.mapper.ResultadoMapper;
import com.its.orientaTest.model.dto.ResultadoResponseDTO;
import com.its.orientaTest.model.entities.Resultado;
import com.its.orientaTest.repository.ResultadoRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;

import java.util.List;

@Service
@AllArgsConstructor
public class ResultadoService {
    private final ResultadoRepository resultadoRepository;
    private final ResultadoMapper resultadoMapper;

    @Transactional(readOnly = true)
    public List<ResultadoResponseDTO> getAllResultados(){
        List<Resultado> resultados = resultadoRepository.findAll();
        return resultadoMapper.toListDTO(resultados);
    }

    @Transactional(readOnly = true)
    public ResultadoResponseDTO getResultadoByTestId(Long test_id){
        Resultado resultado = resultadoRepository.findByTestId(test_id)
        .orElseThrow(() -> new ResourceNotFoundException("Resultado del test " + test_id + "no encontrado"));
        return resultadoMapper.toDTO(resultado);
    }
}
