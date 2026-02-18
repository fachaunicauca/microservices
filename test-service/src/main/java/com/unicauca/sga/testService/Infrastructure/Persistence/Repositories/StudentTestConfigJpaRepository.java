package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories;

import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.StudentTestConfigEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Modifying
    @Query("DELETE FROM StudentTestConfigEntity s WHERE s.test.testId = :testId")
    void deleteByTestId(Integer testId);

    @Query("SELECT s.finalScore " +
            "FROM StudentTestConfigEntity s " +
            "WHERE s.studentEmail IN :emails " +
            "AND s.totalAttemptsUsed > 0 " +
            "AND s.test.testId = :testId " +
            "AND s.finalScore IS NOT NULL")
    List<Double> getScoresByTestIdAndStudentsEmails(@Param("testId") int testId, @Param("emails") Collection<String> emails);

    @Query("SELECT s.finalScore " +
            "FROM StudentTestConfigEntity s " +
            "WHERE s.totalAttemptsUsed > 0 " +
            "AND s.test.testId = :testId " +
            "AND s.finalScore IS NOT NULL")
    List<Double> getScoresByTestId(@Param("testId") int testId);
}
