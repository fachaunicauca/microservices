package com.unicauca.sga.courseService.Infrastructure.Controllers.EnrollStudentController.Mappers;

import com.unicauca.sga.courseService.Domain.Models.Student;
import com.unicauca.sga.courseService.Domain.Models.StudentEnrollment;
import com.unicauca.sga.courseService.Infrastructure.Controllers.EnrollStudentController.DTOs.Response.CourseStudentDTOResponse;
import com.unicauca.sga.courseService.Infrastructure.Controllers.EnrollStudentController.DTOs.Response.StudentEnrollmentDTOResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseStudentEnrollmentDTOMapper {
    CourseStudentDTOResponse studentToDTO(Student student);

    @Mapping(target = "courseId", source = "course.courseId")
    @Mapping(target = "studentId", source = "student.studentId")
    StudentEnrollmentDTOResponse enrollmentToDTO(StudentEnrollment studentEnrollment);
}
