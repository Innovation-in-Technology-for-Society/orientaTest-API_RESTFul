package com.its.orientaTest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UniversidadRequestDTO {
    private String nombre;
    private String correoElectronico;
    private String telefono;
    private String ubicacion;
    private Integer ranking;
}
