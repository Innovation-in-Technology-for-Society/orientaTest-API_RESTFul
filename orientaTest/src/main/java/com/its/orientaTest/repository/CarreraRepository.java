package com.its.orientaTest.repository;

import com.its.orientaTest.model.entities.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarreraRepository extends JpaRepository<Carrera, Long>{
    Optional<Carrera> findByNombre(String nombre);
}
