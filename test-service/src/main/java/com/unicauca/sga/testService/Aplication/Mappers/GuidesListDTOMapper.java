package com.unicauca.sga.testService.Aplication.Mappers;

import com.unicauca.sga.testService.Infrastructure.Controllers.ManageGuidesController.DTOs.Response.TestGuideDTOResponse;
import com.unicauca.sga.testService.Infrastructure.Controllers.ManageGuidesController.DTOs.Response.TestGuideListDTOResponse;
import com.unicauca.sga.testService.Domain.Models.TestGuide;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GuidesListDTOMapper {
    public TestGuideListDTOResponse toDTO(List<TestGuide> testGuideList) {
        TestGuideListDTOResponse testGuideListDTOResponse = new TestGuideListDTOResponse();
        for (TestGuide testGuide : testGuideList) {
            TestGuideDTOResponse testGuideDTOResponse = new TestGuideDTOResponse();
            testGuideDTOResponse.setTestGuideId(testGuide.getTestGuideId());
            testGuideDTOResponse.setTestGuideUrl(testGuide.getTestGuideUrl());
            testGuideListDTOResponse.getTestGuideList().add(testGuideDTOResponse);
        }
        return testGuideListDTOResponse;
    }
}
