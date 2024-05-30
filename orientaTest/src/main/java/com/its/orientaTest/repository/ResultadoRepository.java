package com.its.orientaTest.repository;

import com.its.orientaTest.model.entities.Resultado;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ResultadoRepository extends JpaRepository<Resultado, Long>{
    //Find Resultado By TestId
    Optional<Resultado> findByTestId(Long test_id);
}
