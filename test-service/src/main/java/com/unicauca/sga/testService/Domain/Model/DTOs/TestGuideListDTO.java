package com.unicauca.sga.testService.Domain.Model.DTOs;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
public class TestGuideListDTO {
    private List<TestGuideDTO> testGuideList;
    public TestGuideListDTO() {
        this.testGuideList = new ArrayList<TestGuideDTO>();
    }
}
