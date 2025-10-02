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
        String url;
        try {
            url = cloudinaryService.uploadFile(testGuideDTO.getTestGuideArchive(), testGuideDTO.getTestGuideId());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error uploading safety sheet: " + e.getMessage());
        }

        TestGuide newTestGuide = new TestGuide();
        newTestGuide.setTestGuideId(testGuideDTO.getTestGuideId());
        newTestGuide.setTestGuideUrl(url);
        TestGuide savedTestGuide = testGuidesService.saveTestGuide(newTestGuide);
        return new TestGuideDTO(savedTestGuide.getTestGuideId(), savedTestGuide.getTestGuideUrl());
    }

    public TestGuideListDTO getAllTestGuides() {
        List<TestGuide> testGuideList = testGuidesService.getAllTestsGuides();
        if(testGuideList.isEmpty()){
            throw new NotFoundException("No se encontro ninguna guia.");
        }
        return guidesListDTOMapper.toDTO(testGuideList);
    }

    public boolean deleteTestGuide(String test_guide_id) {

        String formattedId = test_guide_id.replace(" ", "_");
        if(!cloudinaryService.deleteFile(formattedId)){
            throw new NotFoundException("No se encontro ninguna guia con el id: " + test_guide_id);
        }
        testGuidesService.deleteTestGuideById(test_guide_id);
        return true;
    }
}
