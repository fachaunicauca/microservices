package com.unicauca.sga.testService.Infrastructure.Persistence.Tables;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="testGuides")
public class TestGuideEntity {
    @Id
    @Column(length=5000)
    private String testGuideId;

    @Column(length=5000)
    private String testGuideUrl;

    private String teacherEmail;
}
