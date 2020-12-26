package bmnsouza.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import bmnsouza.annotation.constraint.FlagValidator;

@Retention(RUNTIME)
@Constraint(validatedBy = FlagValidator.class)
@Target({ METHOD, FIELD, ANNOTATION_TYPE})
public @interface Flag {
	
	String message() default "Flag inválida";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	
	String[] value() default { };
	
}