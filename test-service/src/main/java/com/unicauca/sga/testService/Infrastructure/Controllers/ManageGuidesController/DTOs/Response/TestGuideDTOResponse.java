package com.unicauca.sga.testService.Infrastructure.Controllers.ManageGuidesController.DTOs.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestGuideDTOResponse {
    private String testGuideId;
    private String testGuideUrl;
}
