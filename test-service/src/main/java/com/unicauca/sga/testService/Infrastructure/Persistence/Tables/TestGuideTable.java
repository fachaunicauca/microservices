package com.unicauca.sga.testService.Infrastructure.Persistence.Tables;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="testGuides")
public class TestGuideTable {
    @Id
    private String testGuideId;

    private String testGuideUrl;

    private String teacherEmail;
}
