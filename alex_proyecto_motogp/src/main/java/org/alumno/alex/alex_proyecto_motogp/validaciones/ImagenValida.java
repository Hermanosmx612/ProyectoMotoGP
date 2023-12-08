package org.alumno.alex.alex_proyecto_motogp.validaciones;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ValidadorImagenes.class })
public @interface ImagenValida {
	String message() default "{imagenValida.mensajePorDefecto}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
