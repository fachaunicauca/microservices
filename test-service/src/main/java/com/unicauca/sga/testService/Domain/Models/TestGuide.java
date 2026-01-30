package com.unicauca.sga.testService.Domain.Models;

import lombok.Data;

@Data
public class TestGuide {
    private String testGuideId;
    private String testGuideUrl;
    private byte[] testGuideArchive;
    private String teacherEmail;
}
