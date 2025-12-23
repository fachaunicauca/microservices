package com.unicauca.sga.testService.Infrastructure.Controllers.ManageGuidesController.DTOs.Request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class TestGuideDTORequest {
    private String testGuideId;
    private MultipartFile testGuideArchive;
}
