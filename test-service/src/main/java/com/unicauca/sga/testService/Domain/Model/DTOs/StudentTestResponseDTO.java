package com.unicauca.sga.testService.Domain.Model.DTOs;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class StudentTestResponseDTO {
    private String subjectName;
    private String teacherName;
    private Date testDate;
    private long studentCode;
    private List<StudentAnswerDTO> studentResponse;
}
