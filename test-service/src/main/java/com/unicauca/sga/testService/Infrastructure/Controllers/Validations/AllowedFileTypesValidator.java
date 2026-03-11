package com.unicauca.sga.testService.Infrastructure.Controllers.Validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

public class AllowedFileTypesValidator implements ConstraintValidator<AllowedFileTypes, MultipartFile> {

    private List<String> allowedTypes;

    @Override
    public void initialize(AllowedFileTypes constraintAnnotation) {
        allowedTypes = Arrays.asList(constraintAnnotation.types());
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {

        if (file == null || file.isEmpty()) {
            return true;
        }

        String contentType = file.getContentType();

        if (contentType == null) {
            return false;
        }

        return allowedTypes.contains(contentType);
    }
}
