package com.unicauca.sga.testService.Domain.Model.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestGuideDTO {
    private String testGuideId;
    private String testGuideUrl;
}
