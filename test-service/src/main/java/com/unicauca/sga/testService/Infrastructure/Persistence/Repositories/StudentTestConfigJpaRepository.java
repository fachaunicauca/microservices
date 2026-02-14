package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories;

import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.StudentTestConfigEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentTestConfigJpaRepository extends JpaRepository<StudentTestConfigEntity, Long> {
    Optional<StudentTestConfigEntity> findByStudentEmailAndTest_TestId(String studentEmail, int testTestId);

    Page<StudentTestConfigEntity> findByAttemptRequestStatusAndTest_TestId(String attemptRequestStatus, Integer testTestId, Pageable pageable);

    List<StudentTestConfigEntity> findAllByTest_TestIdAndStudentEmailIn(Integer testTestId, Collection<String> studentEmails);

    Page<StudentTestConfigEntity> findAllByTest_TestId(Integer testTestId, Pageable pageable);
}
