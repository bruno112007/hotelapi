package com.bruno.hotel.anotacoes.anotacoes_implementacao;

import com.bruno.hotel.anotacoes.Telefone;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TelefoneValidador implements ConstraintValidator<Telefone, String> {

    private static final String CELULAR_PATTERN = "\\(\\d{2}\\)\\s\\d{5}\\-\\d{4}";

    @Override
    public void initialize(Telefone anotacaoTelefoneValidador) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.matches(CELULAR_PATTERN);
    }
}
