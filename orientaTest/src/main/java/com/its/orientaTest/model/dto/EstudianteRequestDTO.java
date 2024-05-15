package com.its.orientaTest.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteRequestDTO {
    @NotBlank(message = "El nombre de estudiante no puede estar vacio")
    private String nombre;

    @NotBlank(message = "Los apellido de estudiante no puede estar vacio")
    private String apellido;

    @NotBlank(message = "El correo de estudiante no puede estar vacio")
    @Email
    private String correoElectronico;

    @NotBlank(message = "El telefono de estudiante no puede estar vacio")
    @Pattern(regexp = "[0-9]+", message = "El numero de telefono solo puede contener digitos")
    private String telefono;

    @NotBlank(message = "La dirección del estudiante no puede estar vacio")
    private String direccion;

    @NotBlank(message = "La contraseña del estudiante no puede estar vacía")
    private String contrasenia;
}
