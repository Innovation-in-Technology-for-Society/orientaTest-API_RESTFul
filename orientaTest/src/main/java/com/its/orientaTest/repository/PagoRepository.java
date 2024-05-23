package com.its.orientaTest.repository;

import com.its.orientaTest.model.entities.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {
    @Query("SELECT p FROM Pago p WHERE p.estudiante.id = :idEstudiante")
    List<Pago> findByIdEstudiante(@Param("idEstudiante") Long idEstudiante);
}