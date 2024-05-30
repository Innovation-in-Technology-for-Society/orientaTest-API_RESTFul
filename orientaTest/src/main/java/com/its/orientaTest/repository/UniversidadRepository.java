package com.its.orientaTest.repository;

import com.its.orientaTest.model.entities.Universidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UniversidadRepository extends JpaRepository<Universidad, Long> {
    // Find Universidad By Name
    Optional<Universidad> findByNombre(String nombre);  
}
