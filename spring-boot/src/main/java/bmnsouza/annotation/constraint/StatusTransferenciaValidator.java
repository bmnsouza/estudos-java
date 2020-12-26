package bmnsouza.annotation.constraint;

import static bmnsouza.constant.TransferenciaConstant.AGUARDANDO_CONFERENCIA;
import static bmnsouza.constant.TransferenciaConstant.CARGA_IGESP;
import static bmnsouza.constant.TransferenciaConstant.CONFERENCIA_BANCO;
import static bmnsouza.constant.TransferenciaConstant.CONTA_INVALIDA;
import static bmnsouza.constant.TransferenciaConstant.RESGATE_CONCLUIDO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.ArrayUtils;

import bmnsouza.annotation.StatusTransferencia;

public class StatusTransferenciaValidator implements ConstraintValidator<StatusTransferencia, Integer> {

	@Override
	public boolean isValid(Integer statusSorteio, ConstraintValidatorContext constraintValidatorContext) {
		Integer[] array = new Integer[] { CONTA_INVALIDA, AGUARDANDO_CONFERENCIA, CONFERENCIA_BANCO, RESGATE_CONCLUIDO, CARGA_IGESP };
		return ArrayUtils.contains(array, statusSorteio);
	}

}