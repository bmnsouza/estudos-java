package bmnsouza.annotation.constraint;

import static bmnsouza.constant.TransferenciaConstant.CONTA_CORRENTE;
import static bmnsouza.constant.TransferenciaConstant.POUPANCA;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.ArrayUtils;

import bmnsouza.annotation.TipoConta;

public class TipoContaValidator implements ConstraintValidator<TipoConta, Integer> {

	@Override
	public boolean isValid(Integer tipoConta, ConstraintValidatorContext constraintValidatorContext) {
		Integer[] array = new Integer[] { CONTA_CORRENTE, POUPANCA };
		return ArrayUtils.contains(array, tipoConta);
	}

}