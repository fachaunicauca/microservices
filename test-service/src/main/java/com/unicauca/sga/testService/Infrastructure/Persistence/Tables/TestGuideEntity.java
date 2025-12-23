package com.unicauca.sga.testService.Infrastructure.Persistence.Tables;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="testGuides")
public class TestGuideEntity {
    @Id
    private String testGuideId;

    private String testGuideUrl;

    private String teacherEmail;
}
