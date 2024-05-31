package com.its.orientaTest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UniversidadPrecisaResponseDTO {  
    private Long id;
    private String nombre;
    private String correoElectronico;
    private String telefono;
    private String ubicacion;
    private Integer ranking;
    private String beneficio;
}
