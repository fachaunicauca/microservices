package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.Specifications;

import com.unicauca.sga.testService.Domain.Constants.TestConstants;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.TestEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.Set;

public class TestSpecification {

    // Filtros para evaluaciones
    private static final Set<String> STRING_FIELDS = Set.of("testTitle", "teacherEmail");
    private static final Set<String> INTEGER_FIELDS = Set.of("courseId");

    public static Specification<TestEntity> withFilter(String filterKey, String filterValue) {
        return (root, query, cb) -> {
            // Si el campo por el que se quiere filtrar (filterKey) llega vacío
            // O si el valor del filtro (filterValue) llega vacío
            // No aplicar el filtro
            if (filterKey == null || filterKey.isBlank() ||
                    filterValue == null || filterValue.isBlank()) return null;

            // Si el campo por el que se quiere filtrar no está permitido
            // No aplicar el filtro
            if (!STRING_FIELDS.contains(filterKey) && !INTEGER_FIELDS.contains(filterKey)) return null;

            // Si el campo es numérico convierte el valor a Integer y usar equal
            if (INTEGER_FIELDS.contains(filterKey)) {
                try {
                    Integer numericValue = Integer.parseInt(filterValue);
                    return cb.equal(root.get(filterKey), numericValue);
                } catch (NumberFormatException e) {
                    return null; // Si el valor no es un numero, no aplicar el filtro
                }
            }

            // Si el campo es String usar like y lower
            return cb.like(
                    cb.lower(root.get(filterKey)),
                    "%" + filterValue.toLowerCase() + "%"
            );
        };
    }

    public static Specification<TestEntity> excludeGeneral() {
        return (root, query, cb) -> cb.notEqual(root.get("testId"), 1);
    }

    public static Specification<TestEntity> byTeacher(String teacherEmail) {
        return (root, query, cb) -> {
            if (teacherEmail == null || teacherEmail.isBlank()) return null;
            return cb.equal(root.get("teacherEmail"), teacherEmail);
        };
    }

    public static Specification<TestEntity> isActive() {
        return (root, query, cb) -> cb.equal(root.get("testState"), TestConstants.ACTIVE);
    }
}
