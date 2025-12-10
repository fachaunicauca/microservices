package com.unicauca.sga.testService.Aplication.UseCases;

import com.unicauca.sga.testService.Aplication.Mappers.GuidesListDTOMapper;
import com.unicauca.sga.testService.Aplication.Services.CloudinaryService;
import com.unicauca.sga.testService.Domain.Exceptions.NotFoundException;
import com.unicauca.sga.testService.Domain.Models.DTOs.TestGuideDTO;
import com.unicauca.sga.testService.Domain.Models.DTOs.TestGuideListDTO;
import com.unicauca.sga.testService.Domain.Models.DTOs.TestGuideRequestDTO;
import com.unicauca.sga.testService.Domain.Models.TestGuide;
import com.unicauca.sga.testService.Domain.Repositories.ITestGuidesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ManageGuidesService {

    private final ITestGuidesRepository testGuidesRepository;
    private final CloudinaryService cloudinaryService;

    private final GuidesListDTOMapper guidesListDTOMapper;

    private boolean testConnection(){
        if (!cloudinaryService.testConnection()){
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Cloudinary not available");
        }
        return true;
    }
    public TestGuideDTO saveTestGuide(TestGuideRequestDTO testGuideDTO) {
        String url = "";
        if(testConnection()){
            try {
                url = cloudinaryService.uploadFile(testGuideDTO.getTestGuideArchive(), testGuideDTO.getTestGuideId());
            } catch (IOException e){
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error uploading file");
            }
        }
        TestGuide newTestGuide = new TestGuide();
        newTestGuide.setTestGuideId(testGuideDTO.getTestGuideId());
        newTestGuide.setTestGuideUrl(url);
        TestGuide savedTestGuide = testGuidesRepository.save(newTestGuide);
        return new TestGuideDTO(savedTestGuide.getTestGuideId(), savedTestGuide.getTestGuideUrl());
    }

    public TestGuideListDTO getAllTestGuides() {
        List<TestGuide> testGuideList = testGuidesRepository.getAllTestsGuides();
        if(testGuideList.isEmpty()){
            throw new NotFoundException("No se encontró ninguna guia.");
        }
        return guidesListDTOMapper.toDTO(testGuideList);
    }

    public boolean deleteTestGuide(String test_guide_id) {
        if(testGuidesRepository.isPresent(test_guide_id)){
            testGuidesRepository.deleteById(test_guide_id);
        }

        String formattedId = test_guide_id.replace(" ", "_");
        String msg = cloudinaryService.deleteFile(formattedId);
        if(!msg.equals("ok")){
            throw new NotFoundException("Ocurrio un error al eliminar el archivo de cloudinary: " + msg);
        }
        return true;
    }
}
