package bmnsouza.annotation.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.ArrayUtils;

import bmnsouza.annotation.ContaBloqueada;

public class ContaBloqueadaValidator implements ConstraintValidator<ContaBloqueada, String> {

	@Override
	public boolean isValid(String contaBloqueada, ConstraintValidatorContext constraintValidatorContext) {
		String[] array = new String[] { "S", "N" };
		return ArrayUtils.contains(array, contaBloqueada);
	}

}