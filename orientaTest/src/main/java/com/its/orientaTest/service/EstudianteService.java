package com.its.orientaTest.service;

import com.its.orientaTest.exceptions.ResourceNotFoundException;
import com.its.orientaTest.mapper.EstudianteMapper;
import com.its.orientaTest.model.dto.EstudianteRequestDTO;
import com.its.orientaTest.model.dto.EstudianteResponseDTO;
import com.its.orientaTest.model.entities.Estudiante;
import com.its.orientaTest.model.entities.enums.Role;
import com.its.orientaTest.repository.EstudianteRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class EstudianteService {
    private final EstudianteRepository estudianteRepository;
    private final EstudianteMapper estudianteMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public EstudianteResponseDTO createEstudiante(EstudianteRequestDTO estudianteRequestDTO){
        Estudiante estudiante = estudianteMapper.toEntity(estudianteRequestDTO);
        estudiante.setContrasenia(passwordEncoder.encode(estudianteRequestDTO.getContrasenia()));
        estudiante.setFechaRegistro(LocalDateTime.now());
        estudiante.setIntentosTest(0);
        estudiante.setRol(Role.USER);

        estudianteRepository.save(estudiante);
        return estudianteMapper.toDTO(estudiante);
    }

    @Transactional(readOnly = true)
    public List<EstudianteResponseDTO> getAllEstudiantes(){
        List<Estudiante> estudiantes = estudianteRepository.findAll();
        return estudianteMapper.toListDTO(estudiantes);
    }

    @Transactional(readOnly = true)
    public EstudianteResponseDTO getEstudianteByNombreApellido(String nombre, String apellido){
        Estudiante estudiante = estudianteRepository.findByNombreApellido(nombre, apellido)
        .orElseThrow(() -> new ResourceNotFoundException("Estudiante nombre:" + nombre + " apellido:" + apellido + " no encontrado"));
        return estudianteMapper.toDTO(estudiante);
    }

    @Transactional
    public void deleteEstudiante(Long id){
        estudianteRepository.deleteById(id);
    }

    public EstudianteResponseDTO actualizarEstudiante(Long id, EstudianteRequestDTO estudianteRequestDTO) {
        // Buscar el estudiante por ID
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado con ID: " + id));
        
        // Actualizar los datos del estudiante con los datos del DTO
        estudianteMapper.updateEntity(estudiante, estudianteRequestDTO);
        
        // Guardar los cambios
        estudiante = estudianteRepository.save(estudiante);
        
        // Convertir el estudiante actualizado a EstudianteResponseDTO
        return estudianteMapper.toDTO(estudiante);
    }

    public EstudianteResponseDTO findByEmail(String email) {
        Estudiante estudiante = estudianteRepository.findByCorreoElectronico(email)
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado con correo: " + email));
        return estudianteMapper.toDTO(estudiante);
    }
}
