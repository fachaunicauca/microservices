package com.unicauca.sga.courseService.Infrastructure.Persistence.Repositories.Adapters;

import com.unicauca.sga.courseService.Domain.Models.Course;
import com.unicauca.sga.courseService.Domain.Repositories.ICourseRepository;
import com.unicauca.sga.courseService.Infrastructure.Persistence.Mappers.CourseMapper;
import com.unicauca.sga.courseService.Infrastructure.Persistence.Repositories.CourseJPARepository;
import com.unicauca.sga.courseService.Infrastructure.Persistence.Repositories.Specifications.CourseSpecifications;
import com.unicauca.sga.courseService.Infrastructure.Persistence.Tables.CourseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CourseRepository implements ICourseRepository {

    private final CourseJPARepository courseJPARepository;
    private final CourseMapper courseMapper;

    @Override
    public Optional<Course> getCourseById(int id) {
        return courseJPARepository.findById(id).map(courseMapper::toModelWithoutStudents);
    }

    @Override
    public Page<Course> getTeacherCoursesPagedAndFiltered(String filterKey,
                                                          String filterValue,
                                                          String teacherEmail,
                                                          int page, int size) {
        Specification<CourseEntity> spec = Specification
                .where(CourseSpecifications.withFilter(filterKey,filterValue))
                .and(CourseSpecifications.byTeacher(teacherEmail));

        return courseJPARepository.findAll(spec, PageRequest.of(
                                                        page,
                                                        size,
                                                        Sort.by(Sort.Direction.DESC,"courseId"))
        ).map(courseMapper::toModelWithoutStudents);
    }

    @Override
    public Page<Course> getAllCoursesPagedAndFiltered(String filterKey,
                                                      String filterValue,
                                                      int page, int size) {

        Specification<CourseEntity> spec = Specification
                .where(CourseSpecifications.withFilter(filterKey,filterValue));

        return courseJPARepository.findAll(spec, PageRequest.of(
                                                            page,
                                                            size,
                                                            Sort.by(Sort.Direction.DESC,"courseId"))
        ).map(courseMapper::toModelWithoutStudents);
    }

    @Override
    public Course saveCourse(Course course) {
        return courseMapper.toModelWithoutStudents(courseJPARepository.save(courseMapper.toInfra(course)));
    }

    @Override
    public void deleteById(int id) {
        courseJPARepository.deleteById(id);
    }

    @Override
    public boolean isPresent(int id) {
        return courseJPARepository.existsById(id);
    }
}
