package com.its.orientaTest.model.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstudianteResponseDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String correoElectronico;
    private String telefono;
    private String direccion;
    private LocalDateTime fechaRegistro;
    private Integer intentosTest; 
}
