package bmnsouza.annotation.constraint;

import static bmnsouza.constant.SorteioPremioConstant.TIPO_PREMIO_CONSUMIDOR;
import static bmnsouza.constant.SorteioPremioConstant.TIPO_PREMIO_ENTIDADE;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.ArrayUtils;

import bmnsouza.annotation.TipoPremio;

public class TipoPremioValidator implements ConstraintValidator<TipoPremio, String> {

	@Override
	public boolean isValid(String tipoPremio, ConstraintValidatorContext constraintValidatorContext) {
		String[] array = new String[] { TIPO_PREMIO_CONSUMIDOR, TIPO_PREMIO_ENTIDADE };
		return ArrayUtils.contains(array, tipoPremio);
	}

}