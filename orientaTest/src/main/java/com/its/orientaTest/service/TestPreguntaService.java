package com.its.orientaTest.service;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;


import java.util.Map;

@Service
@AllArgsConstructor
public class TestPreguntaService {

    private <T> T getMaxKey(Map<T, Integer> map) {
        return map.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse(null);
    }
}
