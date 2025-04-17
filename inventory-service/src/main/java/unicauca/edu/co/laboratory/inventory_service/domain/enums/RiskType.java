package unicauca.edu.co.laboratory.inventory_service.domain.enums;

import java.util.Arrays;

public enum RiskType {
    INFLAMABLE,
    NOCIVO,
    TOXICO,
    FRAGMENTOS,
    CORROSIVO,
    IRRITANTE,
    VENENO,
    COMBUSTIBLE,
    CARCINOGENO,
    CANCERIGENO,
    DANIO_POR_EXPOSICION,
    PELIGROSO,
    OXIDANTE_FUERTE,
    COMBURENTE,
    EXPLOSIVO,
    QUEMADURAS,
    PRECAUCACION,
    PERJUDICIAL,
    ADVERTENCIA;

    public static RiskType fromString(String value) {
        return Arrays.stream(RiskType.values())
                .filter(risk -> risk.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid risk type: " + value));
    }
}
