package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories;

import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.TestEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestJpaRepository extends JpaRepository<TestEntity, Integer> {
    Page<TestEntity> findByTeacherEmail(String teacherEmail, Pageable pageable);

    Page<TestEntity> findByTestIdNot(int testId, Pageable pageable);

    Page<TestEntity> findByTestStateAndTestIdNot(byte testState, int testId, Pageable pageable);
}
