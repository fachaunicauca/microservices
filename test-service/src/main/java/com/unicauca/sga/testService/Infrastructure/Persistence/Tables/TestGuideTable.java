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
    private String test_guide_id;

    @Column
    private String test_guide_url;
}
