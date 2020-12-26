package bmnsouza.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import bmnsouza.annotation.constraint.FlagSNValidator;

/**
 * Verifica se o elemento é um CHAR do tipo 'S' ou 'N' 
 */
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FlagSNValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.TYPE_USE })
public @interface FlagSN {
	String message() default "Caracter inválido, tente 'S' ou 'N'";
	
	Class<?> [] groups() default { };
	
	Class<? extends Payload> [] payload() default { };

}