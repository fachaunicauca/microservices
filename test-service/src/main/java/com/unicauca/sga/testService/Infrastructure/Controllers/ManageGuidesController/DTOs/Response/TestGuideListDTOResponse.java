package com.unicauca.sga.testService.Infrastructure.Controllers.ManageGuidesController.DTOs.Response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TestGuideListDTOResponse {
    private List<TestGuideDTOResponse> testGuideList;
    public TestGuideListDTOResponse() {
        this.testGuideList = new ArrayList<>();
    }
}
