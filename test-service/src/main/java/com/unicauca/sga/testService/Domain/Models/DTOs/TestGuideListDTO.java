package com.unicauca.sga.testService.Domain.Models.DTOs;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TestGuideListDTO {
    private List<TestGuideDTO> testGuideList;
    public TestGuideListDTO() {
        this.testGuideList = new ArrayList<>();
    }
}
