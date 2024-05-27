package com.its.orientaTest.controllers;

import com.its.orientaTest.model.dto.TestResponseDTO;
import com.its.orientaTest.service.TestService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tests")
@AllArgsConstructor
public class TestController {
    private final TestService testService;

    @PostMapping("{test_id}/resultado")
    public ResponseEntity<TestResponseDTO> generateResultado(@PathVariable Long test_id){
        TestResponseDTO finishedTest = testService.generateResultado(test_id);
        return new ResponseEntity<>(finishedTest, HttpStatus.CREATED);
    }
}
