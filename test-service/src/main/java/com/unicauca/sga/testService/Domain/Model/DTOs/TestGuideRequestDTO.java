package com.unicauca.sga.testService.Domain.Model.DTOs;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class TestGuideRequestDTO {
    private String test_guide_id;
    private MultipartFile test_guide_archive;
}
