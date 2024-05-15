package com.its.orientaTest.mapper;

import com.its.orientaTest.model.dto.TestRequestDTO;
import com.its.orientaTest.model.dto.TestResponseDTO;
import com.its.orientaTest.model.entities.Test;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import java.util.List;

@Component
@AllArgsConstructor
public class TestMapper {
    private final ModelMapper modelMapper;

    public Test toEntity(TestRequestDTO testRequestDTO){
        return modelMapper.map(testRequestDTO, Test.class);
    }

    public TestResponseDTO toDTO(Test test){
        return modelMapper.map(test, TestResponseDTO.class);
    }

    public List<TestResponseDTO> toListDTO(List<Test> tests){
        return tests.stream().map(this::toDTO).toList();
    } 
}
