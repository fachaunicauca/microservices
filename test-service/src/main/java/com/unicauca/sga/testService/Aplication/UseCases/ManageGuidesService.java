package com.unicauca.sga.testService.Aplication.UseCases;

import com.unicauca.sga.testService.Aplication.Services.CloudinaryService;
import com.unicauca.sga.testService.Domain.Exceptions.AlreadyExistsException;
import com.unicauca.sga.testService.Domain.Exceptions.NotFoundException;
import com.unicauca.sga.testService.Domain.Models.TestGuide;
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
    private final CloudinaryService cloudinaryService;

    private boolean testConnection(){
        if (!cloudinaryService.testConnection()){
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "El servicio de almacenamiento de archivos no se encuentra disponible");
        }
        return true;
    }

    @Transactional
    public TestGuide saveTestGuide(TestGuide testGuide) {

        if(testGuidesRepository.isPresent(testGuide.getTestGuideId())){
            throw new AlreadyExistsException("La guia con nombre "+ testGuide.getTestGuideId() +" ya existe");
        }

        String url = "";

        if(testConnection()){
            try {
                url = cloudinaryService.uploadFile(testGuide.getTestGuideArchive(), testGuide.getTestGuideId());
            } catch (IOException e){
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Ocurrió un error al guardar el archivo");
            }
        }

        TestGuide newTestGuide = new TestGuide();
        newTestGuide.setTestGuideId(testGuide.getTestGuideId());
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

        if(testGuidesRepository.isPresent(testGuideId)){
            testGuidesRepository.deleteById(testGuideId);
        }

        String formattedId = testGuideId.replace(" ", "_");
        String msg = cloudinaryService.deleteFile(formattedId);

        if(!msg.equals("ok")){
            throw new NotFoundException("Ocurrió un error al eliminar el archivo de Cloudinary: " + msg);
        }

        return true;
    }
}
