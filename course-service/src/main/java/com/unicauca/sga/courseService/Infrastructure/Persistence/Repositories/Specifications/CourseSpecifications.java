package com.unicauca.sga.courseService.Infrastructure.Persistence.Repositories.Specifications;

import com.unicauca.sga.courseService.Infrastructure.Persistence.Tables.CourseEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.Set;

public class CourseSpecifications {
    // Filtros para evaluaciones
    private static final Set<String> ALLOWED_FIELDS = Set.of("courseName", "teacherEmail");

    public static Specification<CourseEntity> withFilter(String filterKey, String filterValue) {
        return (root, query, cb) -> {
            // Si el campo por el que se quiere filtrar (filterKey) llega vacío
            // O si el valor del filtro (filterValue) llega vacío
            // No aplicar el filtro
            if (filterKey == null || filterKey.isBlank() ||
                    filterValue == null || filterValue.isBlank()) return null;

            // Si el campo es String usar like y lower
            return cb.like(
                    cb.lower(root.get(filterKey)),
                    "%" + filterValue.toLowerCase() + "%"
            );
        };
    }

    public static Specification<CourseEntity> byTeacher(String teacherEmail) {
        return (root, query, cb) -> {
            if (teacherEmail == null || teacherEmail.isBlank()) return null;
            return cb.equal(root.get("teacherEmail"), teacherEmail);
        };
    }
}
