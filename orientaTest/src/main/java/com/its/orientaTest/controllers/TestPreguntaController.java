package com.its.orientaTest.controllers;

<<<<<<< HEAD
=======
import com.its.orientaTest.model.dto.TestPreguntaRequestDTO;
>>>>>>> 0e7901caedb59f11eef84d0f5fb2ab371c0b29e2
import com.its.orientaTest.model.dto.TestPreguntaResponseDTO;
import com.its.orientaTest.service.TestPreguntaService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.*;

import java.util.List;

=======
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

>>>>>>> 0e7901caedb59f11eef84d0f5fb2ab371c0b29e2
@RestController
@RequestMapping("/test-preguntas")
@AllArgsConstructor
public class TestPreguntaController {
    private final TestPreguntaService testPreguntaService;

<<<<<<< HEAD
    @GetMapping("/{test_id}/{tipoTest}")
    public ResponseEntity<List<TestPreguntaResponseDTO>> getResultadosTipoTest(
            @PathVariable("test_id") Long test_id,
            @PathVariable("tipoTest") String tipoTest) {
        List<TestPreguntaResponseDTO> resultados = testPreguntaService.getResultadosTipoTest(test_id, tipoTest);
        return new ResponseEntity<>(resultados, HttpStatus.OK);
    }
}

=======
    @PostMapping("{test_id}/{id}/responder")
    public ResponseEntity<TestPreguntaResponseDTO> answerPregunta(
        @PathVariable("test_id") Long test_id,    
        @PathVariable("id") Long id,
        @Validated @RequestBody TestPreguntaRequestDTO testPreguntaRequestDTO){
        
        TestPreguntaResponseDTO answeredPregunta = testPreguntaService.answerPregunta(test_id, id, testPreguntaRequestDTO);
        return new ResponseEntity<>(answeredPregunta, HttpStatus.CREATED); 
    }
}
>>>>>>> 0e7901caedb59f11eef84d0f5fb2ab371c0b29e2
