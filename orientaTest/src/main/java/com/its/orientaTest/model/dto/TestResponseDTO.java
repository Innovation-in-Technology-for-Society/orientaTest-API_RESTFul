package com.its.orientaTest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestResponseDTO {
    private Long id;
    private LocalDateTime fecha_test;
    private Long estudiante_id;
}
