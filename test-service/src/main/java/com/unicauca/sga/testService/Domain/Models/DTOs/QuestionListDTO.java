package com.unicauca.sga.testService.Domain.Models.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class QuestionListDTO {

    private List<QuestionDTO> questions;
}
