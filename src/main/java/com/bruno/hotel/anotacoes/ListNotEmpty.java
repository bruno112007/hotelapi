package com.bruno.hotel.anotacoes;

import com.bruno.hotel.anotacoes.anotacoes_implementacao.NullListValidador;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NullListValidador.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ListNotEmpty {
    String message() default "A lista não pode estar vazia.";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
