package bmnsouza.annotation.constraint;

import static bmnsouza.constant.TransferenciaConstant.BANESE;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.ArrayUtils;

import bmnsouza.annotation.Banco;

public class BancoValidator implements ConstraintValidator<Banco, String> {

	@Override
	public boolean isValid(String banco, ConstraintValidatorContext constraintValidatorContext) {
		String[] array = new String[] { BANESE };
		return ArrayUtils.contains(array, banco);
	}

}