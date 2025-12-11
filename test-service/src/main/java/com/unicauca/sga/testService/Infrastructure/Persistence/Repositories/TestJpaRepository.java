package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories;

import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.TestTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestJpaRepository extends JpaRepository<TestTable, Integer> {
    List<TestTable> findByTeacherEmail(String teacherEmail);
}
