package bmnsouza.annotation.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.ArrayUtils;

import bmnsouza.annotation.UF;

public class UFValidator implements ConstraintValidator<UF, String> {

	@Override
	public boolean isValid(String uf, ConstraintValidatorContext constraintValidatorContext) {
		String[] array = new String[] {
			"AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA",
			"PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO", null
		};
		return ArrayUtils.contains(array, uf);
	}

}