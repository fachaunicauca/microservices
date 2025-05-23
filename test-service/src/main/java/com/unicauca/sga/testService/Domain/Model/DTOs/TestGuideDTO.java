package com.unicauca.sga.testService.Domain.Model.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestGuideDTO {
    private String test_guide_id;
    private String test_guide_url;
}
