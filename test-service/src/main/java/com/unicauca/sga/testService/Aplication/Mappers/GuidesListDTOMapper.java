package com.unicauca.sga.testService.Aplication.Mappers;

import com.unicauca.sga.testService.Domain.Model.DTOs.TestGuideDTO;
import com.unicauca.sga.testService.Domain.Model.DTOs.TestGuideListDTO;
import com.unicauca.sga.testService.Domain.Model.TestGuide;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GuidesListDTOMapper {
    public TestGuideListDTO toDTO(List<TestGuide> testGuideList) {
        TestGuideListDTO testGuideListDTO = new TestGuideListDTO();
        for (TestGuide testGuide : testGuideList) {
            TestGuideDTO testGuideDTO = new TestGuideDTO();
            testGuideDTO.setTest_guide_id(testGuide.getTest_guide_id());
            testGuideDTO.setTest_guide_url(testGuide.getTest_guide_url());
            testGuideListDTO.getTest_guide_list().add(testGuideDTO);
        }
        return testGuideListDTO;
    }
}
