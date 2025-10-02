package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories;

import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.TestTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TestJpaRepository extends JpaRepository<TestTable, Long> {
    List<TestTable> findByTestDateBetweenAndStudentId(LocalDate startDate, LocalDate endDate, Long studentId);
}
