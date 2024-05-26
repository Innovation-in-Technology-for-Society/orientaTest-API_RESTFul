package com.its.orientaTest.repository;

import com.its.orientaTest.model.entities.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PreguntaRepository extends JpaRepository<Pregunta, Long>{
    // Find Vocacional By categoria 1-3
    @Query(value = "SELECT p FROM Pregunta p WHERE p.categoria.id IN (1, 2, 3)")
    List<Pregunta> findVocacional();

}
