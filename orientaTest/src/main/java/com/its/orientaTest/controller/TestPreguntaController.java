package com.its.orientaTest.controller;

import com.its.orientaTest.model.dto.TestPreguntaResponseDTO;
import com.its.orientaTest.service.TestPreguntaService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test-preguntas")
@AllArgsConstructor
public class TestPreguntaController {
    private final TestPreguntaService testPreguntaService;

    @GetMapping("/{test_id}/{tipoTest}")
    public ResponseEntity<List<TestPreguntaResponseDTO>> getResultadosTipoTest(
            @PathVariable("test_id") Long test_id,
            @PathVariable("tipoTest") String tipoTest) {
        List<TestPreguntaResponseDTO> resultados = testPreguntaService.getResultadosTipoTest(test_id, tipoTest);
        return new ResponseEntity<>(resultados, HttpStatus.OK);
    }
}