package com.its.orientaTest.security;

import com.its.orientaTest.model.entities.Estudiante;
import com.its.orientaTest.repository.EstudianteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final EstudianteRepository estudianteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Estudiante estudiante = estudianteRepository
            .findByCorreoElectronico(username)
            .orElseThrow(() -> new UsernameNotFoundException(username));

        return org.springframework.security.core.userdetails.User
            .withUsername(estudiante.getCorreoElectronico())
            .password(estudiante.getContrasenia())
            .roles(estudiante.getRol().name())
            .build();
    }
}
