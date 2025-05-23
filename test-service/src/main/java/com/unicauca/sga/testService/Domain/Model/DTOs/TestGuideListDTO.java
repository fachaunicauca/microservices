package com.unicauca.sga.testService.Domain.Model.DTOs;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
public class TestGuideListDTO {
    private List<TestGuideDTO> test_guide_list;
    public TestGuideListDTO() {
        this.test_guide_list = new ArrayList<TestGuideDTO>();
    }
}
