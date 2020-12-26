package bmnsouza.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import bmnsouza.annotation.constraint.ContaBloqueadaValidator;

/**
 * Verifica se o elemento anotado é uma situação conta bloqueada válida
 * @author Bruno Marcel Nascimento Souza
 */
@Retention(RUNTIME)
@Constraint(validatedBy = ContaBloqueadaValidator.class)
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
public @interface ContaBloqueada {

	String message() default "Situação conta bloqueada inválida";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}