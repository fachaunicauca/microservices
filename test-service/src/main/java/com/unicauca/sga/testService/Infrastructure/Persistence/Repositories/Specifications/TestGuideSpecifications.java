package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.Specifications;

import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.TestGuideEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.Set;

public class TestGuideSpecifications {

    // Filtros para guias
    private static final Set<String> ALLOWED_FIELDS = Set.of("testGuideId", "teacherEmail");

    public static Specification<TestGuideEntity> withFilter(String filterKey, String filterValue) {
        return (root, query, cb) -> {
            // Si el campo por el que se quiere filtrar (filterKey) llega vacío
            // O si el valor del filtro (filterValue) llega vacío
            // No aplicar el filtro
            if (filterKey == null || filterKey.isBlank() ||
                    filterValue == null || filterValue.isBlank()) return null;

            // Si el campo por el que se quiere filtrar no está permitido
            // No aplicar el filtro
            if (!ALLOWED_FIELDS.contains(filterKey)) return null;

            return cb.like(
                    cb.lower(root.get(filterKey)),
                    "%" + filterValue.toLowerCase() + "%"
            );
        };
    }

    public static Specification<TestGuideEntity> byTeacher(String teacherEmail) {
        return (root, query, cb) -> {
            if (teacherEmail == null || teacherEmail.isBlank()) return null;
            return cb.equal(root.get("teacherEmail"), teacherEmail);
        };
    }
}
