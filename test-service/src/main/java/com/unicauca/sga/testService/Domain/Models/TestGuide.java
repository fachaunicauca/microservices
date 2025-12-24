package com.unicauca.sga.testService.Domain.Models;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class TestGuide {
    private String testGuideId;
    private String testGuideUrl;
    private MultipartFile testGuideArchive;
    private String teacherEmail;
}
