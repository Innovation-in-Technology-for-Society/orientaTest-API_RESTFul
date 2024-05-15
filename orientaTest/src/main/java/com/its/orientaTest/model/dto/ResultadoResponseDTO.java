package com.its.orientaTest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoResponseDTO {
    private Long id;
    private String test_id;
    private CarreraUniversidadResponseDTO carreraUniversidad;
}
