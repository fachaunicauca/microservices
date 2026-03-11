package com.unicauca.sga.testService.Infrastructure.Controllers.ManageGuidesController.DTOs.Request;

import com.unicauca.sga.testService.Infrastructure.Controllers.Validations.AllowedFileTypes;
import com.unicauca.sga.testService.Infrastructure.Controllers.Validations.MaxFileSize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class TestGuideDTORequest {
    @NotBlank(message = "Debe especificar el identificador de la guia")
    private String testGuideId;

    @NotNull(message = "Debe seleccionar un archivo a subir")
    @MaxFileSize(value = 8*1024*1024, message = "El archivo no puede superar los 8 MB")
    @AllowedFileTypes(types = {"application/pdf"}, message = "El archivo debe estar en formato PDF")
    private MultipartFile testGuideArchive;

    @NotBlank(message = "La guia debe estar asociada a un docente")
    private String teacherEmail;
}
