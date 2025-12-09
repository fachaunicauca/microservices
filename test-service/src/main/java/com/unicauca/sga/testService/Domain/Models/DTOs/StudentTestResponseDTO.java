package com.unicauca.sga.testService.Domain.Models.DTOs;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class StudentTestResponseDTO {
    private String subjectName;
    private String teacherName;
    private LocalDate testDate;
    private long studentCode;
    private List<StudentAnswerDTO> studentResponse;
}
