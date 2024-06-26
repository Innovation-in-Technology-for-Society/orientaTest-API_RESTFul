package com.its.orientaTest.controllers;

import com.its.orientaTest.model.dto.AuthRequestDTO;
import com.its.orientaTest.model.dto.AuthResponseDTO;
import com.its.orientaTest.model.dto.EstudianteResponseDTO;
import com.its.orientaTest.security.JWTTokenProvider;
import com.its.orientaTest.service.EstudianteService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class JWTController {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JWTTokenProvider tokenProvider;
    private final EstudianteService authService;

    @PostMapping("/token")
    public ResponseEntity<AuthResponseDTO> getAccessToken(@RequestBody AuthRequestDTO authRequest) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        authRequest.getCorreoElectronico(),
                        authRequest.getContrasenia()
                );

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = tokenProvider.createToken(authentication);
        EstudianteResponseDTO estudiante = authService.findByEmail(authRequest.getCorreoElectronico());

        AuthResponseDTO authResponse = new AuthResponseDTO(accessToken, estudiante);

        return ResponseEntity
                .ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .body(authResponse);
    }
}
