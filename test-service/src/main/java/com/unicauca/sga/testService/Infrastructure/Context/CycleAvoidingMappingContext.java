package com.unicauca.sga.testService.Infrastructure.Context;

import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;

import java.util.IdentityHashMap;
import java.util.Map;

/**
 * Contexto de Mapeo para prevenir ciclos infinitos (StackOverflowError)
 * en relaciones bidireccionales de JPA.
 */
public class CycleAvoidingMappingContext {

    // Usa IdentityHashMap para rastrear las referencias de objetos
    private Map<Object, Object> knownInstances = new IdentityHashMap<>();

    /**
     * Se ejecuta antes de iniciar el mapeo de una fuente.
     * Si la instancia de origen ya existe en el mapa, significa que estamos en un ciclo;
     * devuelve la instancia de destino que ya se estaba construyendo.
     */
    @BeforeMapping
    public <T> T getMappedInstance(Object source, @TargetType Class<T> targetType) {
        return (T) knownInstances.get(source);
    }

    /**
     * Se ejecuta cuando una instancia de destino ha sido creada.
     * Almacena la instancia de destino asociada a la fuente, permitiendo a 'getMappedInstance' detener el ciclo.
     */
    @BeforeMapping
    public void storeMappedInstance(Object source, @MappingTarget Object target) {
        knownInstances.put(source, target);
    }
}
