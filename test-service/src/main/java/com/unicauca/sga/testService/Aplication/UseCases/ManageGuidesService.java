package com.unicauca.sga.testService.Aplication.UseCases;

import com.unicauca.sga.testService.Domain.Exceptions.AlreadyExistsException;
import com.unicauca.sga.testService.Domain.Exceptions.NotFoundException;
import com.unicauca.sga.testService.Domain.Models.TestGuide;
import com.unicauca.sga.testService.Domain.Repositories.IFilesRepository;
import com.unicauca.sga.testService.Domain.Repositories.ITestGuidesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ManageGuidesService {

    private final ITestGuidesRepository testGuidesRepository;
    private final IFilesRepository filesRepository;

    @Transactional
    public TestGuide saveTestGuide(TestGuide testGuide) {
        String formattedId = testGuide.getTestGuideId().replace(" ", "_");

        if(testGuidesRepository.isPresent(formattedId)){
            throw new AlreadyExistsException("La guia con nombre "+ testGuide.getTestGuideId() +" ya existe");
        }

        String url = "";
        if(filesRepository.testConnection()){
            url = filesRepository.uploadFile(testGuide.getTestGuideArchive(), formattedId);
        }

        TestGuide newTestGuide = new TestGuide();
        newTestGuide.setTestGuideId(formattedId);
        newTestGuide.setTestGuideUrl(url);

        return testGuidesRepository.save(newTestGuide);
    }

    @Transactional(readOnly = true)
    public List<TestGuide> getAllTestGuides() {
        List<TestGuide> testGuideList = testGuidesRepository.getAllTestsGuides();

        if(testGuideList.isEmpty()){
            throw new NotFoundException("No se encontró ninguna guia.");
        }

        return testGuideList;
    }

    @Transactional
    public boolean deleteTestGuide(String testGuideId) {
        TestGuide testGuide = testGuidesRepository.getTestGuide(testGuideId).orElseThrow(() ->
                new NotFoundException("No se encontró la guia que se quiere eliminar.")
        );

        testGuidesRepository.deleteById(testGuideId);

        return filesRepository.deleteFile(testGuide.getTestGuideId());
    }
}
