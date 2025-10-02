package com.unicauca.sga.testService.Infrastructure.Persistence.Tables;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="test_guides")
public class TestGuideTable {
    @Id
    @Column(name = "test_guide_id")
    private String testGuideId;

    @Column(name = "test_guide_url")
    private String testGuideUrl;
}
