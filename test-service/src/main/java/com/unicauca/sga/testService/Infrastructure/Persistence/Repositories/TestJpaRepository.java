package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories;

import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestJpaRepository extends JpaRepository<TestEntity, Integer> {
    List<TestEntity> findByTeacherEmail(String teacherEmail);
}
