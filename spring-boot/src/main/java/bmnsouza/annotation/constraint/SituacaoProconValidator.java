package bmnsouza.annotation.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.ArrayUtils;

import bmnsouza.annotation.SituacaoProcon;

public class SituacaoProconValidator implements ConstraintValidator<SituacaoProcon, String> {

	@Override
	public boolean isValid(String situacaoProcon, ConstraintValidatorContext constraintValidatorContext) {
		String[] array = new String[] { "ATIVO", "INATIVO", "PENDENTE" };
		return ArrayUtils.contains(array, situacaoProcon);
	}

}