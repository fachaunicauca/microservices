package com.unicauca.sga.courseService.Infrastructure.Persistence.Repositories;

import com.unicauca.sga.courseService.Infrastructure.Persistence.Tables.CourseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseJPARepository extends JpaRepository<CourseEntity, Integer> {
    Page<CourseEntity> findByTeacherEmail(String teacherEmail, Pageable pageable);
}
