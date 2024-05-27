package com.its.orientaTest.repository;

import com.its.orientaTest.model.entities.CarreraUniversidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CarreraUniversidadRepository extends JpaRepository<CarreraUniversidad, Long>{
    //Carrera y Universidad 
    @Query("SELECT cu FROM CarreraUniversidad cu WHERE cu.universidad.id = :universidad_id AND cu.carrera.id = :carrera_id")
    Optional<CarreraUniversidad> findByUniversidadAndCarrera(Long universidad_id, Long carrera_id);
}
