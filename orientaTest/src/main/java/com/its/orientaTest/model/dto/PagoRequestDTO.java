package com.its.orientaTest.model.dto;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoRequestDTO {
    @NotNull(message = "El monto no puede ser nulo")
    @DecimalMin(value = "0.01", message = "El monto debe ser positivo")
    private BigDecimal monto;

    @NotNull(message = "El ID del estudiante no puede ser nulo")
    private Long idEstudiante;
    
    @NotBlank(message = "El beneficio no puede estar en blanco")
    private String beneficio;
}
