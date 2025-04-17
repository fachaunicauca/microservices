package com.unicauca.sga.testService.Infrastructure.Persistence.Tables;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "subject")
public class SubjectTable {
    @Id
    private String subject_name;
}
