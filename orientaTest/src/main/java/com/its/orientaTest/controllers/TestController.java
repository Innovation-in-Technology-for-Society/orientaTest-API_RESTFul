package com.its.orientaTest.controllers;

import com.its.orientaTest.model.dto.TestRequestDTO;
import com.its.orientaTest.model.dto.TestResponseDTO;
import com.its.orientaTest.service.TestService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<TestResponseDTO>> getAllTests(){
        List<TestResponseDTO> tests = testService.getAllTests();
        return new ResponseEntity<>(tests, HttpStatus.OK);
    }
}
