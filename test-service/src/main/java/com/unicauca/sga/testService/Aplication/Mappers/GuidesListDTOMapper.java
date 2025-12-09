package com.unicauca.sga.testService.Aplication.Mappers;

import com.unicauca.sga.testService.Domain.Models.DTOs.TestGuideDTO;
import com.unicauca.sga.testService.Domain.Models.DTOs.TestGuideListDTO;
import com.unicauca.sga.testService.Domain.Models.OldVersion.TestGuide;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GuidesListDTOMapper {
    public TestGuideListDTO toDTO(List<TestGuide> testGuideList) {
        TestGuideListDTO testGuideListDTO = new TestGuideListDTO();
        for (TestGuide testGuide : testGuideList) {
            TestGuideDTO testGuideDTO = new TestGuideDTO();
            testGuideDTO.setTestGuideId(testGuide.getTestGuideId());
            testGuideDTO.setTestGuideUrl(testGuide.getTestGuideUrl());
            testGuideListDTO.getTestGuideList().add(testGuideDTO);
        }
        return testGuideListDTO;
    }
}
