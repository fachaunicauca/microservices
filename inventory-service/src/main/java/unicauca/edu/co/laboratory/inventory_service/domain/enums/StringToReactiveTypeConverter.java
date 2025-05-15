package unicauca.edu.co.laboratory.inventory_service.domain.enums;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToReactiveTypeConverter implements Converter<String, ReactiveType> {
    @Override
    public ReactiveType convert(String source) {
        try {
            return ReactiveType.valueOf(source);
        } catch (IllegalArgumentException e) {
            return ReactiveType.findByFormattedName(source);
        }
    }
}
