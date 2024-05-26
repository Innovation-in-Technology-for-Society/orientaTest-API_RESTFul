package com.its.orientaTest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.its.orientaTest.model.dto.TestResponseDTO;
import com.its.orientaTest.service.TestService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/tests")
@AllArgsConstructor
public class TestController {
    private final TestService testService;
    
    @GetMapping("/{estudiante_id}")
    public ResponseEntity<TestResponseDTO> getTestByEstudianteId(@PathVariable Long estudiante_id){
        TestResponseDTO test = testService.getTestByEstudianteId(estudiante_id);
        return new ResponseEntity<>(test, HttpStatus.OK);
    }    
}
