package com.its.orientaTest.controllers;

import com.its.orientaTest.model.dto.ResultadoResponseDTO;
import com.its.orientaTest.service.ResultadoService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resultados")
@AllArgsConstructor
public class ResultadoController {
    private final ResultadoService resultadoService;

    @GetMapping
    public ResponseEntity<List<ResultadoResponseDTO>> getAllResultados(){
        List<ResultadoResponseDTO> resultados = resultadoService.getAllResultados();
        return new ResponseEntity<>(resultados, HttpStatus.OK);
    }

    @GetMapping("/{test_id}")
    public ResponseEntity<ResultadoResponseDTO> getResultadoByTestId(@PathVariable Long test_id){
        ResultadoResponseDTO resultado = resultadoService.getResultadoByTestId(test_id);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }
}
