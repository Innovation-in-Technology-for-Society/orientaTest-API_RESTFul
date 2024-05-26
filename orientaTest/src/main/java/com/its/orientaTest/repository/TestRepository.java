package com.its.orientaTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.its.orientaTest.model.entities.Test;

public interface TestRepository extends JpaRepository<Test, Long> {
    
}
