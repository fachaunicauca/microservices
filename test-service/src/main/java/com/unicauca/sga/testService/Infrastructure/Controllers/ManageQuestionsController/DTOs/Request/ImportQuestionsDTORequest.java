package com.unicauca.sga.testService.Infrastructure.Controllers.ManageQuestionsController.DTOs.Request;

import com.unicauca.sga.testService.Infrastructure.Controllers.Validations.AllowedFileTypes;
import com.unicauca.sga.testService.Infrastructure.Controllers.Validations.MaxFileSize;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ImportQuestionsDTORequest {

    @NotNull(message = "No se recibió el archivo de importación")
    @MaxFileSize(value = 8*1024*1024, message = "El archivo no puede superar los 8 MB")
    @AllowedFileTypes(types = {"application/xml", "text/xml"}, message = "El archivo debe ser una imagen (JPG, JPEG o PNG)")
    private MultipartFile file;

    @NotNull(message = "No se recibió la lista de indices de preguntas a importar")
    @Size(min = 1, message = "No se seleccionaron las preguntas a importar")
    private List<Integer> selectedIndexes;

    @NotNull(message = "No se indico la evaluación a la que se quieren agregar las preguntas")
    private Integer testId;
}
