package com.unicauca.sga.testService.Aplication.UseCases;

import com.unicauca.sga.testService.Aplication.Mappers.GuidesListDTOMapper;
import com.unicauca.sga.testService.Aplication.Services.CloudinaryService;
import com.unicauca.sga.testService.Domain.Exceptions.NotFoundException;
import com.unicauca.sga.testService.Domain.Model.DTOs.TestGuideDTO;
import com.unicauca.sga.testService.Domain.Model.DTOs.TestGuideListDTO;
import com.unicauca.sga.testService.Domain.Model.DTOs.TestGuideRequestDTO;
import com.unicauca.sga.testService.Domain.Model.TestGuide;
import com.unicauca.sga.testService.Domain.Ports.Services.ITestGuidesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManageGuidesService {

    private final ITestGuidesService testGuidesService;
    private final CloudinaryService cloudinaryService;

    private final GuidesListDTOMapper guidesListDTOMapper;

    public TestGuideDTO saveTestGuide(TestGuideRequestDTO testGuideDTO) {
        String safetySheetUrl;
        try {
            safetySheetUrl = cloudinaryService.uploadFile(testGuideDTO.getTest_guide_archive());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error uploading safety sheet: " + e.getMessage());
        }

        TestGuide newTestGuide = new TestGuide();
        newTestGuide.setTest_guide_id(testGuideDTO.getTest_guide_id());
        newTestGuide.setTest_guide_url(safetySheetUrl);
        TestGuide savedTestGuide = testGuidesService.saveTestGuide(newTestGuide);
        return new TestGuideDTO(savedTestGuide.getTest_guide_id(), savedTestGuide.getTest_guide_url());
    }

    public TestGuideListDTO getAllTestGuides() {
        List<TestGuide> testGuideList = testGuidesService.getAllTestsGuides();
        if(testGuideList.isEmpty()){
            throw new NotFoundException("No se encontro ninguna guia.");
        }
        return guidesListDTOMapper.toDTO(testGuideList);
    }
}
