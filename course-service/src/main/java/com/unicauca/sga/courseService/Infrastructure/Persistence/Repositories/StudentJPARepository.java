package com.unicauca.sga.courseService.Infrastructure.Persistence.Repositories;

import com.unicauca.sga.courseService.Infrastructure.Persistence.Tables.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentJPARepository extends JpaRepository<StudentEntity, Long> {
    Optional<StudentEntity> findByStudentEmail(String studentEmail);
}
