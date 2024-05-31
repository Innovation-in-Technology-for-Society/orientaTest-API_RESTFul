package com.its.orientaTest.controllers;
import com.its.orientaTest.model.dto.TestPreguntaRequestDTO;
import com.its.orientaTest.model.dto.TestPreguntaResponseDTO;
import com.its.orientaTest.service.TestPreguntaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/test-preguntas")
@AllArgsConstructor
public class TestPreguntaController {
    private final TestPreguntaService testPreguntaService;

    @PostMapping("{test_id}/{id}/responder")
    public ResponseEntity<TestPreguntaResponseDTO> answerPregunta(
        @PathVariable("test_id") Long test_id,    
        @PathVariable("id") Long id,
        @Validated @RequestBody TestPreguntaRequestDTO testPreguntaRequestDTO){
        
        TestPreguntaResponseDTO answeredPregunta = testPreguntaService.answerPregunta(test_id, id, testPreguntaRequestDTO);
        return new ResponseEntity<>(answeredPregunta, HttpStatus.CREATED); 
    }
}

    @GetMapping("/{test_id}/{tipoTest}")
    public ResponseEntity<List<TestPreguntaResponseDTO>> getResultadosTipoTest(
            @PathVariable("test_id") Long test_id,
            @PathVariable("tipoTest") String tipoTest) {
        List<TestPreguntaResponseDTO> resultados = testPreguntaService.getResultadosTipoTest(test_id, tipoTest);
        return new ResponseEntity<>(resultados, HttpStatus.OK);
    }
}
