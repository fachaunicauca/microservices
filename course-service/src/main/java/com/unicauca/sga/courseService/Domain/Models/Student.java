package com.unicauca.sga.courseService.Domain.Models;

import lombok.Data;

import java.util.List;

@Data
public class Student {
    private Long studentId;
    private String studentFirstName;
    private String studentLastName;
    private String studentEmail;
}
