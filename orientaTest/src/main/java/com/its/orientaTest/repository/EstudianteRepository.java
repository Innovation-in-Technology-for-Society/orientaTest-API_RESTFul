package com.its.orientaTest.repository;

import com.its.orientaTest.model.entities.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long>{
    // Find Estudiante By Nombre y Apellido
    @Query("SELECT e FROM Estudiante e WHERE e.nombre =?1 AND e.apellido =?2")
    Optional<Estudiante> findByNombreApellido(String nombre, String apellido);
    @Query("SELECT e FROM Estudiante e WHERE e.correoElectronico = ?1")
    Optional<Estudiante> findByCorreoElectronico(String correoElectronico);
    boolean existsByCorreoElectronico(String correoElectronico);
}
