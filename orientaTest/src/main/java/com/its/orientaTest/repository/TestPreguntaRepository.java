package com.its.orientaTest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.its.orientaTest.model.entities.TestPregunta;

public interface TestPreguntaRepository extends JpaRepository<TestPregunta, Long> {
        
}
