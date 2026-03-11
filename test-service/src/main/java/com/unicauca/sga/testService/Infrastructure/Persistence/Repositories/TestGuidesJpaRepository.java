package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories;

import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.TestGuideEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TestGuidesJpaRepository extends JpaRepository<TestGuideEntity, String>,
                                                JpaSpecificationExecutor<TestGuideEntity> {
}
