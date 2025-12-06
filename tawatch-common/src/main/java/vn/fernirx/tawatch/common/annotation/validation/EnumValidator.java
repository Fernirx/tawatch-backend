package vn.fernirx.tawatch.common.annotation.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Validator;

import java.util.Arrays;
import java.util.List;

public class EnumValidator implements ConstraintValidator<ValidEnum,String> {
    private List<String> enumValues;
    private boolean allowNull;
    private boolean ignoreCase;

    @Override
        public void initialize(ValidEnum annotation) {
        allowNull = annotation.allowNull();
        ignoreCase = annotation.ignoreCase();
        Enum<?>[] enums = annotation.enumClass().getEnumConstants();
        enumValues = Arrays.stream(enums)
                .map(Enum::name)
                .toList();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) return allowNull;
        return ignoreCase
                ? enumValues.stream().anyMatch(e -> e.equalsIgnoreCase(value))
                : enumValues.contains(value);
    }
}
