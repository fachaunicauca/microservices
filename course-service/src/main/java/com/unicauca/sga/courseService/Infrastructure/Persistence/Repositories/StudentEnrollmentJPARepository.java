package com.unicauca.sga.courseService.Infrastructure.Persistence.Repositories;

import com.unicauca.sga.courseService.Domain.Models.Student;
import com.unicauca.sga.courseService.Domain.Models.StudentEnrollment;
import com.unicauca.sga.courseService.Infrastructure.Persistence.Tables.StudentEnrollmentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentEnrollmentJPARepository extends JpaRepository<StudentEnrollmentEntity, Long> {
    Page<StudentEnrollmentEntity> findByCourse_CourseId(Integer courseCourseId, Pageable pageable);

    Optional<StudentEnrollmentEntity> findByStudent_StudentIdAndCourse_CourseId(Long studentStudentId, Integer courseCourseId);

    boolean existsByStudent_StudentIdAndCourse_CourseId(Long studentStudentId, Integer courseCourseId);
}
