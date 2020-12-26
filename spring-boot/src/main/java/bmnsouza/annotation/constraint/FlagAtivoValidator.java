package bmnsouza.annotation.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.ArrayUtils;

import bmnsouza.annotation.FlagAtivo;

public class FlagAtivoValidator implements ConstraintValidator<FlagAtivo, String> {

	@Override
	public boolean isValid(String flag, ConstraintValidatorContext constraintValidatorContext) {
		String[] array = new String[] { "0", "1", null };
		return ArrayUtils.contains(array, flag);
	}

}