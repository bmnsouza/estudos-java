package bmnsouza.annotation.constraint;

import static bmnsouza.constant.SorteioConstant.BILHETES_GERADOS;
import static bmnsouza.constant.SorteioConstant.CREDITOS_SORTEIO_EXPIRADOS;
import static bmnsouza.constant.SorteioConstant.SORTEIO_CADASTRADO;
import static bmnsouza.constant.SorteioConstant.SORTEIO_REALIZADO;
import static bmnsouza.constant.SorteioConstant.TRANSFERENCIA_PREMIOS_REALIZADA;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.ArrayUtils;

import bmnsouza.annotation.StatusSorteio;

public class StatusSorteioValidator implements ConstraintValidator<StatusSorteio, Integer> {

	@Override
	public boolean isValid(Integer statusSorteio, ConstraintValidatorContext constraintValidatorContext) {
		Integer[] array = new Integer[] { SORTEIO_CADASTRADO, BILHETES_GERADOS, SORTEIO_REALIZADO, TRANSFERENCIA_PREMIOS_REALIZADA, CREDITOS_SORTEIO_EXPIRADOS };
		return ArrayUtils.contains(array, statusSorteio);
	}

}