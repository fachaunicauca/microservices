package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories;

import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.TestAttemptTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestAttemptJpaRepository extends JpaRepository<TestAttemptTable, Long> {
}
