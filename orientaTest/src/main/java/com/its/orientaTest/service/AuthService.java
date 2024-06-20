package com.its.orientaTest.service;

import com.its.orientaTest.exceptions.ResourceNotFoundException;
import com.its.orientaTest.exceptions.BadRequestException;
import com.its.orientaTest.mapper.EstudianteMapper;
import com.its.orientaTest.model.dto.AuthRequestDTO;
import com.its.orientaTest.model.dto.AuthResponseDTO;
import com.its.orientaTest.model.dto.EstudianteRequestDTO;
import com.its.orientaTest.model.dto.EstudianteResponseDTO;
import com.its.orientaTest.model.entities.Estudiante;
import com.its.orientaTest.model.entities.enums.Role;
import com.its.orientaTest.repository.EstudianteRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AuthService {
    private final EstudianteRepository estudianteRepository;
    private final PasswordEncoder passwordEncoder;
    private final EstudianteMapper estudianteMapper;

    public EstudianteResponseDTO signup(EstudianteRequestDTO signupFormDTO) {
        boolean emailAlreadyExists = estudianteRepository.existsByCorreoElectronico(signupFormDTO.getCorreoElectronico());
        if (emailAlreadyExists) {
            throw new BadRequestException("El email ya está siendo usado por otro usuario.");
        }
        Estudiante estudiante = estudianteMapper.toEntity(signupFormDTO);
        estudiante.setContrasenia(passwordEncoder.encode(signupFormDTO.getContrasenia()));
        estudiante.setRol(Role.USER);
        estudiante.setFechaRegistro(LocalDateTime.now());
        estudianteRepository.save(estudiante);
        return estudianteMapper.toDTO(estudiante);
    }

    public AuthResponseDTO login(AuthRequestDTO authRequestDTO) {
        Estudiante estudiante = estudianteRepository.findByCorreoElectronico(authRequestDTO.getCorreoElectronico())
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado con correo: " + authRequestDTO.getCorreoElectronico()));

        if (!passwordEncoder.matches(authRequestDTO.getContrasenia(), estudiante.getContrasenia())) {
            throw new BadRequestException("Correo electrónico o contraseña incorrectos.");
        }

        // Como no se está utilizando token, devolvemos el estudiante autenticado sin token
        EstudianteResponseDTO estudianteResponseDTO = estudianteMapper.toDTO(estudiante);
        return new AuthResponseDTO(null, estudianteResponseDTO); // Token es null
    }

    public EstudianteResponseDTO findByEmail(String email) {
        Estudiante estudiante = estudianteRepository.findByCorreoElectronico(email)
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado con correo: " + email));
        return estudianteMapper.toDTO(estudiante);
    }
}
