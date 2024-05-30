package com.its.orientaTest.repository;

import com.its.orientaTest.model.entities.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long>{
    
}
