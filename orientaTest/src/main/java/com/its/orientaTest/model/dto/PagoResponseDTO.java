package com.its.orientaTest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PagoResponseDTO {
    private Long idPago;
    private String nombreEstudiante;
    private BigDecimal monto;
    private String beneficio;
}
