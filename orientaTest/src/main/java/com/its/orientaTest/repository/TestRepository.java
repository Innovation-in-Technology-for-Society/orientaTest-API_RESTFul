package com.its.orientaTest.repository;

import com.its.orientaTest.model.entities.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long>{

}
