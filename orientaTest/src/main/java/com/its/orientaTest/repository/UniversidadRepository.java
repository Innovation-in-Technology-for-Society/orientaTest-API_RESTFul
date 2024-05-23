package com.its.orientaTest.repository;

import com.its.orientaTest.model.entities.Universidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversidadRepository extends JpaRepository<Universidad, Long> {
    
}