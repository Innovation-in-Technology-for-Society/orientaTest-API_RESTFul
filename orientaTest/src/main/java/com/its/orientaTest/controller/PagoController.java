package com.its.orientaTest.controller;

import com.its.orientaTest.model.dto.PagoBeneficioResponseDTO;
import com.its.orientaTest.service.PagoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/pagos")
@AllArgsConstructor
public class PagoController {
    private final PagoService pagoService;

    @GetMapping("/{id}")
    public ResponseEntity<List<PagoBeneficioResponseDTO>> getBeneficioById(@PathVariable Long id){
        List<PagoBeneficioResponseDTO> pagoBeneficioResponseDTO = pagoService.getPagosBeneficioByEstudianteId(id);
        return new ResponseEntity<>(pagoBeneficioResponseDTO, HttpStatus.OK);
    }
}