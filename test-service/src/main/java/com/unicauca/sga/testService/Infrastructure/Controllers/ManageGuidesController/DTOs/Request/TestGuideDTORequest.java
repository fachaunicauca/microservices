package com.unicauca.sga.testService.Infrastructure.Controllers.ManageGuidesController.DTOs.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class TestGuideDTORequest {
    @NotBlank(message = "Debe especificar el identificador de la guia")
    private String testGuideId;
    @NotNull(message = "Debe seleccionar un archivo a subir")
    private MultipartFile testGuideArchive;
}
