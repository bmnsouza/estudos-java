package bmnsouza.annotation.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.ArrayUtils;

import bmnsouza.annotation.Situacao;

public class SituacaoValidator implements ConstraintValidator<Situacao, String> {

	@Override
	public boolean isValid(String situacao, ConstraintValidatorContext constraintValidatorContext) {
		String[] array = new String[] { "ATIVO", "INATIVO" };
		return ArrayUtils.contains(array, situacao);
	}

}