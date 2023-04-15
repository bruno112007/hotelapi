package com.bruno.hotel.anotacoes.anotacoes_implementacao;

import com.bruno.hotel.anotacoes.ListNotEmpty;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class NullListValidador implements ConstraintValidator<ListNotEmpty, List<?>>{

    @Override
    public void initialize(ListNotEmpty constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<?> list, ConstraintValidatorContext constraintValidatorContext) {
        return list != null && !list.isEmpty();
    }
}
