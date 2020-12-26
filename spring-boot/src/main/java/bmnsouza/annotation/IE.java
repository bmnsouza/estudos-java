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
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import bmnsouza.annotation.IE;

/**
 * Esta annotation tem o objetivo de validar o número da inscrição estadual.
 * 
 * Permite valores nulos.
 * 
 * @author gaadleao
 *
 */
@Retention(RUNTIME)
@Constraint(validatedBy = IEValidator.class)
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
public @interface IE {

	String message() default "Inscrição Estadual inválida";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}

class IEValidator implements ConstraintValidator<IE, String> {

	@Override
	public boolean isValid(String ie, ConstraintValidatorContext constraintValidatorContext) {
		boolean isValid = false;

		if (ie == null) {
			isValid = true;
		} else if (ie.length() != 9 || !"27".equals(ie.substring(0, 2))) {
			isValid = false;
		} else {			
			try {
				isValid = obterDigito(ie.substring(0, 8)).equals(ie.substring(8));
			} catch (Exception ex) {
				isValid = false;
			}
		}
	
		return isValid;
	}

	private String obterDigito(final String ie) throws Exception {
		String digito = null;
		
		int tamanho = ie.length();
		int numero1 = 0;
		int faixa = 9;
		
		for (int i = 0; i < tamanho; ++i) {
			numero1 += ((tamanho - i - 1) % faixa + 2) * Character.digit(ie.charAt(i), 10);
		}
		
		final int numero2 = numero1 % 11;
		if (numero2 == 0 || numero2 == 1) {
			digito = "0";
		} else {
			digito = Integer.toString(11 - numero2);
		}
		
		return digito;
	}
	
}