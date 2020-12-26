package bmnsouza.annotation.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.ArrayUtils;

import bmnsouza.annotation.Estado;

public class EstadoValidator implements ConstraintValidator<Estado, String> {

	@Override
	public boolean isValid(String estado, ConstraintValidatorContext constraintValidatorContext) {
		String[] array = new String[] { "ATIVO", "BLOQUEADO", "INATIVO" };
		return ArrayUtils.contains(array, estado);
	}

}