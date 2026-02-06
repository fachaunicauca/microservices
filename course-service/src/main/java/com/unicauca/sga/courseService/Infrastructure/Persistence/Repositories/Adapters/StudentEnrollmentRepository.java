package com.unicauca.sga.courseService.Infrastructure.Persistence.Repositories.Adapters;

import com.unicauca.sga.courseService.Domain.Models.Student;
import com.unicauca.sga.courseService.Domain.Models.StudentEnrollment;
import com.unicauca.sga.courseService.Domain.Repositories.IStudentEnrollmentRepository;
import com.unicauca.sga.courseService.Infrastructure.Persistence.Mappers.StudentEnrollmentMapper;
import com.unicauca.sga.courseService.Infrastructure.Persistence.Mappers.StudentMapper;
import com.unicauca.sga.courseService.Infrastructure.Persistence.Repositories.StudentEnrollmentJPARepository;
import com.unicauca.sga.courseService.Infrastructure.Persistence.Tables.StudentEnrollmentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StudentEnrollmentRepository implements IStudentEnrollmentRepository {

    private final StudentEnrollmentJPARepository studentEnrollmentJPARepository;
    private final StudentEnrollmentMapper studentEnrollmentMapper;
    private final StudentMapper studentMapper;

    @Override
    public Page<Student> getCourseStudents(int courseId, int page, int size) {
        Page<StudentEnrollmentEntity> enrollments =
                studentEnrollmentJPARepository.findByCourse_CourseId(
                        courseId,
                        PageRequest.of(page, size)
        );

        return enrollments.map(enrollment ->
                studentMapper.toModel(enrollment.getStudent())
        );
    }

    @Override
    public Optional<StudentEnrollment> getStudentEnrollment(long studentId, int courseId) {
        return studentEnrollmentJPARepository.findByStudent_StudentIdAndCourse_CourseId(studentId, courseId)
                .map(studentEnrollmentMapper::toModel);
    }

    @Override
    public StudentEnrollment saveStudentEnrollment(StudentEnrollment studentEnrollment) {
        return studentEnrollmentMapper.toModel(studentEnrollmentJPARepository.save(
                studentEnrollmentMapper.toInfra(studentEnrollment)
        ));
    }

    @Override
    public boolean isStudentInCourse(long studentId, int courseId) {
        return studentEnrollmentJPARepository.existsByStudent_StudentIdAndCourse_CourseId(studentId, courseId);
    }

    @Override
    public void deleteById(long id) {
        studentEnrollmentJPARepository.deleteById(id);
    }

    @Override
    public void deleteByCourseId(int courseId) {
        studentEnrollmentJPARepository.deleteByCourse_CourseId(courseId);
    }

    @Override
    public void deleteByStudentId(long studentId) {
        studentEnrollmentJPARepository.deleteByStudent_StudentId(studentId);
    }
}
