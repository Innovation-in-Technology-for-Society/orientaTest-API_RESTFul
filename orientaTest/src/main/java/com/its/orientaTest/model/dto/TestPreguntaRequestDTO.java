package com.its.orientaTest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestPreguntaRequestDTO {
    private Long test_id;
    private Long pregunta_id;
    private Integer valor;
}
