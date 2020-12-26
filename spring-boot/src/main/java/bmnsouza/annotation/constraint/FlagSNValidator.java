package bmnsouza.annotation.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.ArrayUtils;

import bmnsouza.annotation.FlagSN;

public class FlagSNValidator implements ConstraintValidator<FlagSN, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		String[] array =  new String[] { "S", "N", null };
		return ArrayUtils.contains(array, value);
	}
	
}
