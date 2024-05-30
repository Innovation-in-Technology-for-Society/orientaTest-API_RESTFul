package com.its.orientaTest.repository;

import com.its.orientaTest.model.entities.TestPregunta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestPreguntaRepository extends JpaRepository<TestPregunta, Long>{
    List<TestPregunta> findByTestId(Long test_id);
}
