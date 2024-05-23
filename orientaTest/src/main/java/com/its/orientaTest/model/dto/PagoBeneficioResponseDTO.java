package com.its.orientaTest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoBeneficioResponseDTO {
    private Long idPago;
    private String nombreEstudiante;
    private String beneficio;
}
