package com.its.orientaTest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestPreguntaResponseDTO {
    private Long test_id;
    private Long pregunta_id;
    private Integer valor;
}
