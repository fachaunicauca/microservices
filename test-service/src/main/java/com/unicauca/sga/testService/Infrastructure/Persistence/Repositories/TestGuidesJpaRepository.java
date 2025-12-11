package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories;

import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.TestGuideTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestGuidesJpaRepository extends JpaRepository<TestGuideTable, String> {
    List<TestGuideTable> findByTeacherEmail(String teacherEmail);
}
