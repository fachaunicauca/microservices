package com.unicauca.sga.testService.Domain.Model.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class QuestionListDTO {

    private List<QuestionDTO> questions;
}
