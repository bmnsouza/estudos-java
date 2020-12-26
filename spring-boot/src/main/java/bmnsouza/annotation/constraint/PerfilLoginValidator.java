package bmnsouza.annotation.constraint;

import static bmnsouza.constant.LoginConstant.CONSUMIDOR;
import static bmnsouza.constant.LoginConstant.CONTADOR;
import static bmnsouza.constant.LoginConstant.CONTRIBUINTE;
import static bmnsouza.constant.LoginConstant.ENTIDADE;
import static bmnsouza.constant.LoginConstant.FAZENDARIO;
import static bmnsouza.constant.LoginConstant.PROCON;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.ArrayUtils;

import bmnsouza.annotation.PerfilLogin;

public class PerfilLoginValidator implements ConstraintValidator<PerfilLogin, String> {

	@Override
	public boolean isValid(String perfilLogin, ConstraintValidatorContext constraintValidatorContext) {
		String[] array = new String[] { CONSUMIDOR, CONTADOR, CONTRIBUINTE, ENTIDADE, FAZENDARIO, PROCON };
		return ArrayUtils.contains(array, perfilLogin);
	}

}