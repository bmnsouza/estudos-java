package bmnsouza.annotation.constraint;

import static bmnsouza.constant.DocumentoFiscalConstant.CUPOM_FISCAL;
import static bmnsouza.constant.DocumentoFiscalConstant.MODELO_01;
import static bmnsouza.constant.DocumentoFiscalConstant.MODELO_02;
import static bmnsouza.constant.DocumentoFiscalConstant.MODELO_ONLINE;
import static bmnsouza.constant.DocumentoFiscalConstant.NOTA_FISCAL_CONSUMIDOR_ELETRONICA;
import static bmnsouza.constant.DocumentoFiscalConstant.NOTA_FISCAL_ELETRONICA;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.ArrayUtils;

import bmnsouza.annotation.TipoNotaFiscal;

public class TipoNotaFiscalValidator implements ConstraintValidator<TipoNotaFiscal, String> {

	@Override
	public boolean isValid(String tipoNotaFiscal, ConstraintValidatorContext constraintValidatorContext) {
		String[] array = new String[] { CUPOM_FISCAL, MODELO_01, MODELO_02, MODELO_ONLINE, NOTA_FISCAL_CONSUMIDOR_ELETRONICA, NOTA_FISCAL_ELETRONICA };
		return ArrayUtils.contains(array, tipoNotaFiscal);
	}

}