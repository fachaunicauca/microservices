package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories;

import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.TestGuideEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestGuidesJpaRepository extends JpaRepository<TestGuideEntity, String> {
    List<TestGuideEntity> findByTeacherEmail(String teacherEmail);
}
