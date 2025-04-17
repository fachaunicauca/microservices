package com.unicauca.sga.testService.Domain.Model.DTOs;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class StudentTestResponseDTO {
    private String subject_name;
    private String teacher_name;
    private Date test_date;
    private long student_code;
    private List<StudentAnswerDTO> student_response;
}
