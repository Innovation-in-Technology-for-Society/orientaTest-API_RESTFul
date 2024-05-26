package com.its.orientaTest.controllers;

import com.its.orientaTest.model.dto.TestRequestDTO;
import com.its.orientaTest.model.dto.TestResponseDTO;
import com.its.orientaTest.service.TestService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tests")
@AllArgsConstructor
public class TestController {
    private final TestService testService;

    @PostMapping
    public ResponseEntity<TestResponseDTO> startTest(@Validated @RequestBody TestRequestDTO testRequestDTO){
        TestResponseDTO takenTest = testService.startTest(testRequestDTO);
        return new ResponseEntity<>(takenTest, HttpStatus.CREATED);
    }
}
