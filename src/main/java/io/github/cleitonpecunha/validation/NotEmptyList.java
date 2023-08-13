package io.github.cleitonpecunha.validation;

import io.github.cleitonpecunha.validation.constraintValidation.NotEmptyListValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = NotEmptyListValidation.class)

public @interface NotEmptyList {
    String message() default "A lista de ITENS DO PEDIDO, não pode ser vazia.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
