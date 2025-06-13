package unicauca.edu.co.laboratory.inventory_service.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum ReactiveType {
    ALDEHIDOS_5("5-ALDEHIDOS"),
    METALES_Y_ELEMENTOS_ACTIVOS_NO22_Y_POCO_ACTIVOS_23("METALES_Y_ELEMENTOS_ACTIVOS_No22_Y_POCO_ACTIVOS_23"),
    SALES_DE_ACIDOS_INORGANICOS_ORGANICOS_NO38("SALES_DE_ACIDOS_INORGANICOS/ORGANICOS_No38_(SULFATOS_Y_TARTRATOS)"),
    ACIDOS_INORGANICOS_NO_OXIDANTES_1("1-ACIDOS_INORGANICOS_NO_OXIDANTES"),
    ACIDOS_INORGANICOS_OXIDANTES_2("2-ACIDOS_INORGANICOS_OXIDANTES"),
    AGENTES_INORGANICOS_OXIDANTES("AGENTES_INORGANICOS_OXIDANTES_NITRATOS_No27-CLORATOS_No44-PEROXIDOS,_CROMATOS_Y_DICROMATOS"),
    AMIDAS_Y_AMINAS("AMIDAS_No6-AMINAS_E_HIDROXILAMINAS_No7,_COMPUESTOS_AZO_No8,_BASES,_OXIDOS,_CARBONATOS,_AMONIACO_Y_DERIVADOS_No10"),
    COMPUESTOS_INORGANICOS("COMPUESTOS_INORGANICOS_ENTRE_REDUCTORES_Y_OXIDANTES_No46_(CLORUROS)"),
    ESTERES_13("ESTERES_No13"),
    ALCOHOLES_Y_FENOLES("4-ALCOHOLES,_14_ETERES,_19-CETONAS,_31-FENOLES"),
    SALES_DE_BASES_INORGANICAS("39-SALES_DE_BASES_INORGANICAS/ORGANICAS_(FOSFATOS_Y_OTROS)"),
    ACIDOS_CARBOXILICOS("ACIDOS_CARBOXILICOS_U_ORGANICOS"),
    COMPUESTOS_INERTES_98("98-COMPUESTOS_INERTES"),
    HIDROCARBUROS_AROMATICOS("16-HIDROCARBUROS_AROMATICOS_(INDICADORES)");

    private final String formattedName;

    ReactiveType(String formattedName) {
        this.formattedName = formattedName;
    }

    @JsonValue
    public String getFormattedName() {
        return formattedName;
    }

    @JsonCreator
    public static ReactiveType findByFormattedName(String formattedName) {
        return Arrays.stream(ReactiveType.values())
                .filter(type -> type.getFormattedName().equals(formattedName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid ReactiveType: " + formattedName));
    }
}
