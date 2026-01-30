package com.unicauca.sga.testService.Aplication.Services;

import com.unicauca.sga.testService.Domain.Models.Question.Question;
import com.unicauca.sga.testService.Domain.Repositories.IFilesRepository;
import com.unicauca.sga.testService.Domain.Repositories.IQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class QuestionImageService {
    private final IFilesRepository filesRepository;
    private final IQuestionRepository questionRepository;

    public void syncQuestionImage(Question question){
        // Si no hay conexión al repositorio de almacenamiento de archivos se rompe el flujo
        if(!filesRepository.testConnection()){
            return;
        }

        // Caso 1: se recibió una imagen, subirla al almacenamiento de archivos y, en dado caso, eliminar la anterior
        byte[] image = question.getQuestionImage();

        String oldImageId = null;

        if(question.getQuestionId() != null) {
            oldImageId = questionRepository.getById(question.getQuestionId())
                    .map(Question::getQuestionImageId)
                    .orElse(null);
        }

        if(image != null){
            String imageId = "question"+question.getQuestionId()+"image";

            // Si tenia una imagen, borrarla del almacenamiento
            if(oldImageId != null){
                filesRepository.deleteFile(oldImageId);
            }

            String url = filesRepository.uploadFile(image, imageId);

            question.setQuestionImageId(imageId);
            question.setQuestionImageUrl(url);
        }// Caso 2: si no se recibió una imagen, pero tenía una y se borró, eliminarla del almacenamiento de archivos
        else if(oldImageId != null && question.getQuestionImageId() == null){
            filesRepository.deleteFile(oldImageId);
        }
    }

    public void cleanupQuestionImage(Question question){
        if(question.getQuestionImageId() == null){
            return;
        }

        if(!filesRepository.testConnection()){
            return;
        }

        filesRepository.deleteFile(question.getQuestionImageId());
    }

}
